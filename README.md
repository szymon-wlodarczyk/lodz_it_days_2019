# lodz_it_days_2019
Łódź IT days 2019 workshop code. "IoT cloud system implemented based on Azure services".

Link to presentation: https://www.slideshare.net/SzymonWodarczyk1/iot-cloud-system-implemented-based-on-azure-services

Repository is splitted into three parts:
- infrastructure contains terraform code which setup Azure resources,
- device-simulator contains spring boot application which sends data over MQTT protocol to Azure IoT Hub endpoint,
- events-processing-service contains spring boot application which receives events from Azure Event Hub and stores data in SQL database.


