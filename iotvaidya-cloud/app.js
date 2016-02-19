var express = require('express');
var bodyParser = require('body-parser');
var fs = require('fs');
var request = require('request');
var multer = require('multer');
//var btoa = require('btoa');   //photo --> string
//var atob = require('atob');  //string --> photo
var watson = require('watson-developer-cloud');

var sinchAuth = require('sinch-auth');
var sinchSms = require('sinch-messaging');
var auth = sinchAuth("aecd746a-460e-4b04-8078-b5e1aab32910 ", "Smdg+GaSOkSgCEcJ+0DgFg==");


//var https = require('https');



/*
var language_translation = watson.language_translation({
  username: "05b2ba96-ccc7-49f9-b8db-2211dd45c70a",
  password: "VscuqFQvZVLK",
  version: 'v2'
});
*/
/*
var dialog = watson.dialog({
  username: 'f3c6e0ba-66f3-48f4-b64f-157ee5c3bc09',
  password: 'IXU0wmtGS3oR',
  version: 'v1'
});
dialog.getDialogs({}, function (err, dialogs) {
  if (err)
    console.log('error:', err);
  else
    console.log(JSON.stringify(dialogs, null, 2));
});
*/
//var mqtt    = require('mqtt');
var mongoose = require('mongoose');
//var twilio = require('twilio');

//var cfenv = require('cfenv');
var app = express();
var path = require("path");

app.use(express.static(path.join(__dirname,'public')));
//var port = process.env.PORT || 3000;
app.use(bodyParser());

var port = (process.env.VCAP_APP_PORT || 3000);
var host = (process.env.VCAP_APP_HOST || 'localhost');

//var twilioSid = "AC581178cb6a95199205fe4c972de5587c";
//var twilioToken = "832de9e852927aa58e1ea962fa1ba78c";
/*
var config = JSON.parse(process.env.VCAP_SERVICES);
var twilioSid, twilioToken;
config['user-provided'].forEach(function(service) {
    if (service.name == 'messageme') {
        twilioSid = service.credentials.accountSID;
        twilioToken = service.credentials.authToken;
    }
});

*/

// for your local testing
dbURI = 'mongodb://arunkumar271:touchwood123@ds029804.mongolab.com:29804/mydb'
/* 
// for MongoDB by Compose service
if (process.env.VCAP_SERVICES) {
  var env = JSON.parse(process.env.VCAP_SERVICES);
 
  if (env['user-provided']) { // for Compose
      var cm = env['user-provided'][0].credentials;
      var dbname = 'mongo'; // you noted this earlier
      dbURI = 'mongodb://'
          + cm.user
          + ':' + cm.password
          + '@' + cm.uri
          + ':' + cm.port
          + '/' + dbname;
  }
}
*/
var db = mongoose.connection;
db.on('disconnected', function() {
    console.log('disconnected');
    console.log('dbURI is: '+dbURI);
    mongoose.connect(dbURI,
      {server: {auto_reconnect:true,
      socketOptions: { keepAlive: 1, connectTimeoutMS: 30000 }},
      replset: { socketOptions: { keepAlive: 1, connectTimeoutMS : 30000 } }});
});
console.log('dbURI is: '+dbURI);
mongoose.connect(dbURI, {server:{auto_reconnect:true}});

var pSchema = mongoose.Schema({
  name : String,
  age : Number,
  contact : Number,
  village : String,
  temp : Number,
  pulse: Array,
  ecg : Array,
  gsr : Array
});
var dSchema = mongoose.Schema({
  name : String,
  email : String,
  pass: String
});
var patient = mongoose.model('patient', pSchema, "test");
var doc = mongoose.model('doc', dSchema, "doc");    //(new_name_of_schema,schema_object,collection_name)

//var layout = {fileopt : "overwrite", filename : "simple-node-example"};
//mqtt code
var Client = require("ibmiotf").IotfApplication;

var config = {
    "org" : "tbqwz5",
    "id" : "iotvaidya",
    "auth-key" : "a-tbqwz5-xicyl8hlnv",
    "auth-token" : "qRCrwVofk*X-QdYGMU"
}
var appClient = new Client(config);

appClient.connect();

appClient.on("connect", function () {

    appClient.subscribeToDeviceEvents("lappy");

});
appClient.on("deviceEvent", function (deviceType, deviceId, eventType, format, payload) {
    console.log("Device Event from : "+deviceType+" : "+deviceId+" of event "+eventType+" with payload : "+payload);

    var par = JSON.parse(payload);
    //var pay =  new patient(payload);
  /*  if(par.pic_Id){
      
      var chunk = payload;
    function reconstructBase64String(chunk) {
 pChunk = JSON.parse(chunk["d"]);

 //creates a new picture object if receiving a new picture, else adds incoming strings to an existing picture 
 if (pictures[pChunk["pic_id"]]==null) {
 pictures[pChunk["pic_id"]] = {"count":0, "total":pChunk["size"], pieces: {}, "pic_id": pChunk["pic_id"]};

 pictures[pChunk["pic_id"]].pieces[pChunk["pos"]] = pChunk["data"];

 }

 else {
 pictures[pChunk["pic_id"]].pieces[pChunk["pos"]] = pChunk["data"];
 pictures[pChunk["pic_id"]].count += 1;


 if (pictures[pChunk["pic_id"]].count == pictures[pChunk["pic_id"]].total) {
 console.log("Image reception complete");
 var str_image=""; 

 for (var i = 0; i <= pictures[pChunk["pic_id"]].total; i++) 
 str_image = str_image + pictures[pChunk["pic_id"]].pieces[i];

// convert base64 string back to image 
    base64_decode(str_image, './public/dist/img/patient/profile/Ujjwal.jpg');
  
 // function to create file from base64 encoded string
function base64_decode(base64str, file) {
    // create buffer object from base64 encoded string, it is important to tell the constructor that the string is base64 encoded
    var bitmap = new Buffer(base64str, 'base64');
    // write buffer to file
    fs.writeFileSync(file, bitmap);
    console.log('******** File created from base64 encoded string ********');
}
    

   } 

   
 }
}

    }
    else{
      */
    // function to create file from base64 encoded string
function base64_decode(base64str, file) {
    // create buffer object from base64 encoded string, it is important to tell the constructor that the string is base64 encoded
    var bitmap = new Buffer(base64str, 'base64');
    // write buffer to file
    fs.writeFileSync(file, bitmap);
    console.log('******** File created from base64 encoded string ********');
}
 
    var pay = new patient({
      name : par.name,
      age : par.age,
      contact : par.contact,
      village : par.village,
      temp : par.temp,
      pulse: par.pulse,
      ecg : par.ecg,
      gsr : par.gsr
})
    // convert base64 string to image 
    base64_decode(par.photo, path.join(__dirname, 'public/dist/img/patient/profile/'+par.name+'.jpg'));

/*
    // function to create file from base64 encoded string
function base64_decode(base64str, file) {
    // create buffer object from base64 encoded string, it is important to tell the constructor that the string is base64 encoded
    var bitmap = new Buffer(base64str, 'base64');
    // write buffer to file
    fs.writeFileSync(file, bitmap);
    console.log('******** File created from base64 encoded string ********');
}
    // convert base64 string back to image 
    base64_decode(par.image, './public/dist/img/patient/profile/Ujjwal.jpg');
    
*/
    pay.save(function(err){
    if ( err ) throw err;
    console.log("Patient's data Saved Successfully in DB");
  });
//}
});
//mqtt end


app.get('/',function(req,res){
  res.sendFile(path.join(__dirname, 'public/pages/examples/login.html'));
});

var storage =   multer.diskStorage({
  destination: function (req, file, callback) {
    callback(null, './public/dist/img/doc/');
  },
  filename: function (req, file, callback) {
    callback(null, req.body.name + '.jpg');
  }
});
var upload = multer({ storage : storage}).single('imgupload');

app.post('/register',function(req,res){
  //res.sendFile(path.join(__dirname, 'public/report.html'));
  
  upload(req,res,function(err) {
        if(err) {
            return res.end("Error uploading file.");  //on the server
        }
        var n = req.body.name;
        var e = req.body.email;
        var p = req.body.password;
        //console.log(e);
        var d = new doc({
        name : n,
        email : e,
        pass : p
        });
        d.save(function(err){
          if ( err ) throw err;
          console.log("Doc Saved Successfully" + '\n' + d); //to the database
          //res.sendFile(path.join(__dirname, 'public/pages/examples/login.html'));
          res.redirect("/");
        });
      });//upload
});//register

app.post('/login',function(req,res){
  //res.sendFile(path.join(__dirname, 'public/check.html'));
  var i = 0;
  var n;
  var e = req.body.email;
  var p = req.body.password;
  var url = 'https://api.mongolab.com/api/1/databases/mydb/collections/doc?apiKey=CM-x_SrL-88CIOt3rbzX5_K0cdceAzxO';
  request(url, function(err, resp, body) {
   body = JSON.parse(body);
   //res.sendFile(path.join(__dirname, 'public/pages/tables/entry.html'));

   body.forEach(function(element,index){
     if((element.email == e)&&(element.pass == p)){
      n = element.name;
      i++;
    }
    });
   if(i>0){
    //res.sendFile(path.join(__dirname, 'public/pages/tables/entry.html'));
    res.redirect('/entry?'+n);
  }
    else
      //res.sendFile(path.join(__dirname, 'public/pages/examples/login.html'));
    res.redirect("/");

  });
   
});

app.get('/register',function(req,res){
  res.sendFile(path.join(__dirname, 'public/pages/examples/register.html'));
}); 
app.get('/login',function(req,res){
  res.sendFile(path.join(__dirname, 'public/pages/examples/login.html'));
  //res.redirect('/'); 
}); 
 app.get('/logout',function(req,res){                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       
  res.redirect('/');                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  
   
}); 
/*
app.get('/data',function(req,res){
  patient.find({},{ecg:1,_id:0},function(err,datashow){
	if(err)
		console.log(err);

	res.send(JSON.stringify({ 'datashow' : datashow }));
   });
   
});
*/
/*
app.get('/message', function (req, res) {
    var client = new twilio.RestClient(twilioSid, twilioToken);

    client.sendMessage({
        to:'+917507012952',
        from:'+12403770856',
        body:'IOT VAIDYA is there for you!'
    }, function(err, message) {
        res.send('Message sent! ID: '+message.sid);
    });
});
*/
app.post('/message', function (req, res) {
    //var client = new twilio.RestClient(twilioSid, twilioToken);
    var msg = req.body.message;
    //var number = req.body.contact_number;
    var t_url = 'http://api.mymemory.translated.net/get?q='+msg+'&langpair=en|hi'   //from mymemory.translated.net
    request(t_url, function(err, resp, url_body) {
      var t = JSON.parse(url_body);
      var msg_translated = t.responseData.translatedText;
     /* var data = JSON.stringify({
      api_key: "1a52938f",
      api_secret: "6cebea2a",
      to: "917507012952",
      from: "917507012952",
      text: msg_translated
      });

var options = {
  host: 'rest.nexmo.com',
  path: '/sms/json',
  port: 443,
  method: 'POST',
  headers: {
    'Content-Type': 'application/json',
    'Content-Length': Buffer.byteLength(data)
  }
};

var re = https.request(options);

re.write(data);
re.end();
*/
      sinchSms.sendMessage("+917507012952", msg_translated);
      //console.log(sinchSms.getStatus("122725968"));
      //console.log(msg_translated);
      //console.log(number);
      /*client.sendMessage({     //twilio
        to:'+917507012952',
        from:'+12403770856',
        body:msg_translated
    }, function(err, message) {
        res.redirect("/");
    });
*/
    res.redirect("/");
    });

});//post

app.get('/entry',function(req,res){
  res.sendFile(path.join(__dirname, 'public/pages/tables/entry.html'));
});
app.get('/report',function(req,res){
  res.sendFile(path.join(__dirname, 'public/report.html'));
});

app.get('/map',function(req,res){                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       
  res.sendFile(path.join(__dirname, 'public/pages/map.html'));                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  
   
});

app.listen(port, host,function(){
	console.log('Running on PORT : ' + port);
});

