import paho.mqtt.client as mqtt
import json

# Set MQTT broker information
broker_address = ""
broker_port = 8883
receive_topic = "/sub"
response_topic = "/pub"
ca_file = r".pem"
username = ""
password = ""
job_id = ""


# Callback function when the client receives a message
def on_message(client, userdata, message):
    global job_id

    try:
        # Check if message payload is empty
        if not message.payload:
            raise ValueError("Received an empty message payload")

        # Extract jobId and serviceType from the received message
        received_data = json.loads(message.payload.decode())
        if isinstance(received_data, list):
            received_message = received_data[0]
            job_id = received_message.get("id", "")
            service_type = received_message["serviceType"]
            action = received_message.get("action", "")
        else:
            received_message = received_data
            job_id = received_message.get("id", "")
            service_type = received_message["serviceType"]
            action = received_message.get("action", "")

        # Only process messages with serviceType == "ericsson"
        if service_type == "ericsson" and action == "prov":
            # Extract the id field from the initial message
            print("Got job type: " + service_type + "With action: " + action)
            id_value = ""
            if "services" in received_message:
                services = received_message["services"]
                if len(services) > 0:
                    id_value = services[0].get("id", "")
                    print(id_value)
            print("Received message with jobId {} and serviceType {}".format(id_value, service_type))
            # Add the jobId and id fields to the response message
            response_message = {
                "jobId": id_value,
                "serviceType": "ericsson",
                "action": "ack",
                "jobType": "cvc",
                "status": {
                    "status": "success",
                    "message": "",
                    "retries": 0,
                    "statusCode": 0
                },
                "ticket": "",
                "error": False,
                "services": []
            }

            # Send the response message
            client.publish(response_topic, json.dumps(response_message))
            print("Ack sent for jobId: " + id_value)
            print("Response is: " + json.dumps(response_message))

        if service_type == "ericsson" and action == "revoke":
            # Extract the id field from the initial message
            print("Got job type: " + service_type + "With action: " + action)
            id_value = ""
            if "services" in received_message:
                services = received_message["services"]
                if len(services) > 0:
                    id_value = services[0].get("id", "")
                    print(id_value)
            print("Received revoke message with jobId {} and serviceType {}".format(id_value, service_type))
            # Add the jobId and id fields to the response message
            response_message = {
                "jobId": id_value,
                "serviceType": "ericsson",
                "action": "ack",
                "jobType": "cvc",
                "status": {
                    "status": "success",
                    "message": "",
                    "retries": 0,
                    "statusCode": 0
                },
                "ticket": "",
                "error": False,
                "services": []
            }

            # Send the response message
            client.publish(response_topic, json.dumps(response_message))
            print("Ack sent for revoke jobId: " + id_value)
            print("Response is: " + json.dumps(response_message))
    except Exception as e:
        # Handle any exceptions that might occur
        print("An error occurred while processing the message: {}".format(e))


# Callback function when the client connects to the MQTT broker
def on_connect(client, userdata, flags, rc):
    if rc == 0:
        # Send a "hello" message to the desired topic
        client.publish(response_topic, "Hello")
        print("Connected to MQTT broker and sent a hello message")
    else:
        print("Failed to connect to MQTT broker")


# Create MQTT client instance and set callback functions
client = mqtt.Client()
client.on_message = on_message
client.on_connect = on_connect

# Set TLS support and CA file
client.tls_set(ca_certs=ca_file)

# Set username and password
client.username_pw_set(username, password)

# Connect to the MQTT broker and subscribe to the receive topic
client.connect(broker_address, broker_port)
client.subscribe(receive_topic)

# Start the MQTT client loop to receive messages
client.loop_forever()
