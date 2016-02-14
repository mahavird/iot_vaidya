var _auth;

module.exports = function(appKey, secret){
	if (appKey && secret){
		_auth = "Basic " + new Buffer("application\\" + appKey + ":" + secret).toString("base64");
	}

	return _auth;
};