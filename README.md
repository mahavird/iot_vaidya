# iot_vaidya
#Project name : IOT VAIDYA – AN IOT MEDIC.
#Project focus : HEALTHCARE
#Introduction :
There is a huge shortage of doctors in the rural areas; a huge imbalance exists in the number of doctors working in the rural areas as compared to the urban areas. This situation of lack of man power has been further worsened by unwillingness of doctors to work in these regions. In such kind of a scenario, getting basic health checkups is difficult and regular health checkup for people is a distant dream.
But no worries now. IOT VAIDYA! - An IoT Medic which helps bridging gap between remote areas and doctors is there for you."Vaidya" is the hindi translation for Doctor.This is a standalone solution meant specifically for people living in remote places where there is shortage of doctors.It covers all the necessary steps right from the basic diagnosis to the prescription generation.
#How does it work?
We are inspired by a vision of providing basic diagnosis to even the economically lowly out there.So we aim at creating a low cost diagnosis system which acquires the vitals from various bio-medical sensors and sends it to the cloud.This way, the doctors sitting far away,in any corner of the world will be able to diagnose the patients health.This is not all it.We have also aimed at setting up algorithms in the cloud for a very basic analysis of the data so that instant feedback can be sent back to the patient regarding the preventive measures to be taken.
Technology behind IoT Vaidya:
#SOFTWARE(iotvaidya-cloud):
1. MQTT: Obviously IoT is the backbone of our project.We are using MQTT protocol for “publishing” and “subscribing” the patients data.We will be using paho-mqtt project for implementing the same.
2. Cloud:Current implementation is on IBM Bluemix.It has IOTF service which implements MQTT protocol.
3. The backend is developed using Nodejs.Nodejs is used to write server for our application on cloud.This application will subscribe to all the patients data from IOTF broker.
There are couple of other service we have used like Twilio for SMS delivery and watson-developer-cloud for Language Translation.Since we are interested in Hindi Language currently and watson doesn't have it on the list currently, so we used mymemory.translated.net APIs instead.
4. Database being used here is provided by MongoLab.This will be required to store the subscribed patient data.
5. Front End (UI) : We will be using HTML,CSS,JQUERY and AJAX  displaying all the data in an elegant way.
6. Analytics : The embedded analytics on the cloud will be used.Application deployed on cloud will also do analysis of the data.General inference regarding disease spread can be found.

#HARDWARE:
1. Gateway :The gateway is created using Eclipse Kura and Reactive Blocks on Raspberry Pi Model B+.
2. Sensors: ECG electrodes , Grove – GSR(galvanic skin response),Temperature and Pulse sensor. The prototype will be flexible and many more medical sensors can be interfaced.
3. Additional : USB Keyboard(for entering patient ID and name) and TFT(for display)

#Innovative aspect of Project : 
The project will bridge the gap between doctors and patients especially in rural areas.It will be amazing how the doctor far away will sit in the hands of a normal person residing in either at home or some Public Health Center.

#Value to developers and Community : 
Iot in healthcare is a hot topic.The situation demands some way to implement IoT for the end user.We want to do that exactly.Developer community will benefit from our project as we will do our solution opensource and it will be totally transparent.We do use a lot of technologies in IoT implementation.IOT vaidya might act as one standard method to go in the direction of IoT healthcare.
