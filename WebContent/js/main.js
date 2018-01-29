/* 
 * /*Custom Overlay window
 * 
 * var myOptions = { disableAutoPan: false ,maxWidth: 0 ,pixelOffset: new
 * google.maps.Size(-140, 0) ,zIndex: null ,boxStyle: { backgroundColor:
 * "WhiteSmoke" ,borderRadius:"20px" ,opacity: 1 ,width: "700px"
 * ,height:"400px" ,padding:"1px" ,border:"solid 1px red" ,overflow:"auto" }
 * ,closeBoxMargin: "2px 1px 1px 1px" ,closeBoxURL:
 * "http://www.google.com/intl/en_us/mapfiles/close.gif" ,infoBoxClearance:
 * new google.maps.Size(1, 1) ,isHidden: false ,pane: "floatPane"
 * ,enableEventPropagation: false }; infoWindow = new InfoBox(myOptions);
 */

function initialize() {
	geocoder = new google.maps.Geocoder();
	marker = new google.maps.Marker();
	infoWindow = new google.maps.InfoWindow();
	var myLatlng = new google.maps.LatLng(42.3581, -71.0636);
	image = 'images/thumbs/rss_big.png';

	var mapOptions = {
		center : myLatlng,
		zoom : ZOOM_LEVEL_CITY,
		styles : [ {
			"featureType" : "water",
			"stylers" : [ {
				"color" : "#19a0d8"
			} ]
		}, {
			"featureType" : "administrative",
			"elementType" : "labels.text.stroke",
			"stylers" : [ {
				"color" : "#ffffff"
			}, {
				"weight" : 6
			} ]
		}, {
			"featureType" : "administrative",
			"elementType" : "labels.text.fill",
			"stylers" : [ {
				"color" : "#000000"
			} ]
		}, {
			"featureType" : "road.highway",
			"elementType" : "geometry.stroke",
			"stylers" : [ {
				"color" : "#efe9e4"
			}, {
				"lightness" : -40
			} ]
		}, {
			"featureType" : "road.arterial",
			"elementType" : "geometry.stroke",
			"stylers" : [ {
				"color" : "#efe9e4"
			}, {
				"lightness" : -20
			} ]
		}, {
			"featureType" : "road",
			"elementType" : "labels.text.stroke",
			"stylers" : [ {
				"lightness" : 100
			} ]
		}, {
			"featureType" : "road",
			"elementType" : "labels.text.fill",
			"stylers" : [ {
				"lightness" : -100
			} ]
		}, {
			"featureType" : "road.highway",
			"elementType" : "labels.icon"
		}, {
			"featureType" : "landscape",
			"elementType" : "labels",
			"stylers" : [ {
				"visibility" : "off"
			} ]
		}, {
			"featureType" : "landscape",
			"stylers" : [ {
				"lightness" : 20
			}, {
				"color" : "#efe9e4"
			} ]
		}, {
			"featureType" : "landscape.man_made",
			"stylers" : [ {
				"visibility" : "off"
			} ]
		}, {
			"featureType" : "water",
			"elementType" : "labels.text.stroke",
			"stylers" : [ {
				"lightness" : 100
			} ]
		}, {
			"featureType" : "water",
			"elementType" : "labels.text.fill",
			"stylers" : [ {
				"lightness" : -100
			} ]
		}, {
			"featureType" : "poi",
			"elementType" : "labels.text.fill",
			"stylers" : [ {
				"hue" : "#11ff00"
			} ]
		}, {
			"featureType" : "poi",
			"elementType" : "labels.text.stroke",
			"stylers" : [ {
				"lightness" : 100
			} ]
		}, {
			"featureType" : "poi",
			"elementType" : "labels.icon",
			"stylers" : [ {
				"hue" : "#4cff00"
			}, {
				"saturation" : 58
			} ]
		}, {
			"featureType" : "poi",
			"elementType" : "geometry",
			"stylers" : [ {
				"visibility" : "on"
			}, {
				"color" : "#f0e4d3"
			} ]
		}, {
			"featureType" : "road.highway",
			"elementType" : "geometry.fill",
			"stylers" : [ {
				"color" : "#efe9e4"
			}, {
				"lightness" : -25
			} ]
		}, {
			"featureType" : "road.arterial",
			"elementType" : "geometry.fill",
			"stylers" : [ {
				"color" : "#efe9e4"
			}, {
				"lightness" : -10
			} ]
		} ],
	};

	map = new google.maps.Map(document.getElementById("map-canvas"), mapOptions);
	// Create the DIV to hold the control and call the CenterControl()
	// constructor
	// passing in this DIV.
	var centerControlDiv = document.createElement('div');
	var centerControl = new CenterControl(centerControlDiv, map);
	centerControlDiv.index = 1;
	map.controls[google.maps.ControlPosition.LEFT_BOTTOM]
			.push(centerControlDiv);

	var newsTypeDiv = document.createElement('div');
	var newsTypeControl = NewsTypeControl(newsTypeDiv, map);
	newsTypeDiv.index = 1;
	map.controls[google.maps.ControlPosition.RIGHT_TOP].push(newsTypeDiv);

	var input = document.getElementById('address');
	var autocomplete = new google.maps.places.Autocomplete(input);
	autocomplete.bindTo('bounds', map);
	autocomplete.addListener('place_changed', function() {
		marker.setMap(null);
		var place = autocomplete.getPlace();
		placeMarkerAutoComplete(place);
	});

	if (navigator.geolocation) {
		navigator.geolocation.getCurrentPosition(function(position) {
			// Get the coordinates of the current position.
			var lat = position.coords.latitude;
			var lng = position.coords.longitude;
			myLatlng = new google.maps.LatLng(lat, lng);
			map.panTo(myLatlng);
		});
	}

	// action listeners
	google.maps.event.addListener(map, 'click', function(e) {
		placeMarker(e);
	});

	google.maps.event.addListener(map, 'zoom_changed', function(e) {
		ChangeZoomLabel(e);
	});

	google.maps.event.addListener(infoWindow, 'closeclick', function() {
		marker.setMap(null);
	}); // removes the marker

	/*
	 * google.maps.event.addListener(autocomplete, 'place_changed', function() {
	 * infoWindow.close(); marker.setVisible(false); var place =
	 * autocomplete.getPlace(); if (!place.geometry) { return; } });
	 * autocomplete.setTypes(['geocode']); // read local xml file var
	 * xmlhttp=new XMLHttpRequest(); xmlhttp.open("GET","countries.xml",false);
	 * xmlhttp.send(); xmlDoc=xmlhttp.responseXML; //implement hashtable
	 * hashtable = new Object(); for (var i = 0; i <
	 * xmlDoc.documentElement.childNodes.length; i++) { if (
	 * xmlDoc.documentElement.childNodes[i].nodeName == 'row' ) { var country =
	 * xmlDoc.documentElement.childNodes[i].childNodes[1].textContent; var
	 * domain = xmlDoc.documentElement.childNodes[i].childNodes[3].textContent;
	 * hashtable[country] =domain; } }
	 */

	var geolocationoptions = {
		enableHighAccuracy : true,
		timeout : 5000,
		maximumAge : 0
	};
}

var controlText;
var newsTypeText;
var weatherTypeText;
var pollutionTypeText;
var newsType;
/**
 * The CenterControl adds a control to the map that indicates Geographic
 * Location type. This constructor takes the control DIV as an argument.
 * 
 * @constructor
 */
function CenterControl(controlDiv, map) {

	// Set CSS for the control border.
	var controlUI = document.createElement('div');
	controlUI.style.backgroundColor = '#fff';
	controlUI.style.border = '2px solid #fff';
	controlUI.style.borderRadius = '3px';
	controlUI.style.boxShadow = '0 2px 6px rgba(0,0,0,.3)';
	controlUI.style.cursor = 'pointer';
	controlUI.style.marginBottom = '10px';
	controlUI.style.textAlign = 'center';
	controlUI.title = 'Check Zoom Level here';
	controlDiv.appendChild(controlUI);

	// Set CSS for the control interior.
	controlText = document.createElement('div');
	controlText.style.color = 'rgb(25, 25, 25)';
	controlText.style.fontFamily = 'Roboto,Arial,sans-serif';
	controlText.style.fontSize = '16px';
	controlText.style.lineHeight = '38px';
	controlText.style.paddingLeft = '5px';
	controlText.style.paddingRight = '5px';
	controlText.innerHTML = 'City';
	controlUI.appendChild(controlText);
	map.setZoom(ZOOM_LEVEL_CITY);
}

function NewsTypeControl(controlDiv, map) {
	// Set CSS for the control border.
	var controlUI = document.createElement('div');
	controlUI.style.backgroundColor = '#F0F0F0';
	controlUI.style.borderRadius = '3px';
	controlUI.style.boxShadow = '0 2px 6px rgba(0,0,0,.3)';
	controlUI.style.cursor = 'pointer';
	controlUI.style.marginBottom = '1px';
	controlUI.style.textAlign = 'center';
	controlDiv.appendChild(controlUI);

	newsType = NEWS_TYPE;
	// Set CSS for the control interior.
	newsTypeText = document.createElement('div');
	newsTypeText.style.color = 'rgb(25, 25, 25)';
	newsTypeText.style.fontFamily = 'Roboto,Arial,sans-serif';
	newsTypeText.style.fontSize = '16px';
	newsTypeText.style.lineHeight = '38px';
	newsTypeText.style.paddingLeft = '5px';
	newsTypeText.style.paddingRight = '5px';
	newsTypeText.innerHTML = 'News';
	newsTypeText.style.borderTop = '2px solid #0000FF';

	// Set CSS for the control interior.
	weatherTypeText = document.createElement('div');
	weatherTypeText.style.color = 'rgb(25, 25, 25)';
	weatherTypeText.style.fontFamily = 'Roboto,Arial,sans-serif';
	weatherTypeText.style.fontSize = '16px';
	weatherTypeText.style.lineHeight = '38px';
	weatherTypeText.style.paddingLeft = '5px';
	weatherTypeText.style.paddingRight = '5px';
	weatherTypeText.innerHTML = 'Weather';

	pollutionTypeText = document.createElement('div');
	pollutionTypeText.style.color = 'rgb(25, 25, 25)';
	pollutionTypeText.style.fontFamily = 'Roboto,Arial,sans-serif';
	pollutionTypeText.style.fontSize = '16px';
	pollutionTypeText.style.lineHeight = '38px';
	pollutionTypeText.style.paddingLeft = '5px';
	pollutionTypeText.style.paddingRight = '5px';
	pollutionTypeText.innerHTML = 'Pollution';

	controlUI.appendChild(newsTypeText);
	controlUI.appendChild(weatherTypeText);
	//controlUI.appendChild(pollutionTypeText);
	
	newsTypeText.addEventListener('click', function() {
		newsTypeText.style.borderTop = '2px solid #0000FF';
		weatherTypeText.style.borderTop = '2px solid #FFFFFF';
		pollutionTypeText.style.borderTop = '2px solid #FFFFFF';
		newsType = NEWS_TYPE;
	}, false);
	
	weatherTypeText.addEventListener('click', function() {
		weatherTypeText.style.borderTop = '2px solid #0000FF';
		newsTypeText.style.borderTop = '2px solid #FFFFFF';
		pollutionTypeText.style.borderTop = '2px solid #FFFFFF';
		newsType = WEATHER_TYPE;
	}, false);
	
	pollutionTypeText.addEventListener('click', function() {
		pollutionTypeText.style.borderTop = '2px solid #0000FF';
		weatherTypeText.style.borderTop = '2px solid #FFFFFF';
		newsTypeText.style.borderTop = '2px solid #FFFFFF';
		newsType = POLLUTION_TYPE;
	}, false);
	
}


// indicates map zoom level set by the user
function ChangeZoomLabel(event) {
	if (map.getZoom() >= ZOOM_LEVEL_STREET) {
		controlText.innerHTML = 'Street';
	} else if (map.getZoom() <= ZOOM_LEVEL_STREET
			&& map.getZoom() >= ZOOM_LEVEL_ZIP) {
		controlText.innerHTML = 'Zip';
	} else if (map.getZoom() <= ZOOM_LEVEL_ZIP
			&& map.getZoom() >= ZOOM_LEVEL_CITY) {
		controlText.innerHTML = 'City';
	} else if (map.getZoom() <= ZOOM_LEVEL_CITY
			&& map.getZoom() >= ZOOM_LEVEL_STATE) {
		controlText.innerHTML = 'State';
	} else if (map.getZoom() <= ZOOM_LEVEL_STATE
			&& map.getZoom() >= ZOOM_LEVEL_COUNTRY) {
		controlText.innerHTML = 'Country';
	} else {
		controlText.innerHTML = 'Country';
	}
}

/*
 * Pans the map position to current location
 */
function panToCurrentPosition(position, map) {
	geocoder
		.geocode(
				{
					'location' : myLatlng
				},
				function(results, status) {
					if (status == google.maps.GeocoderStatus.OK) {
						if (results[0]) {
							map.panTo(myLatlng);
						}
					}
				});
}

/*
 * Entry point for all clicks on Map Geocodes locations and place marker
 */
function placeMarker(position) {
	// clear marker
	marker.setMap(null);
	myLatlng = position.latLng;
	GeoCodeLocation();
}


function placeMarkerAutoComplete(place) {
	// clear marker
	myLatlng = new google.maps.LatLng(
			place.geometry.location.lat(),
			place.geometry.location.lng());
	buttonClickSearchLocation(place);
}

function GeoCodeLocation() {
	if (myLatlng != null) {
		geocoder.geocode({
			'latLng' : myLatlng
		}, function(results, status) {
			if (status == google.maps.GeocoderStatus.OK) {
				if (results[0] != undefined) {
					// set new marker
					marker = new google.maps.Marker({
						position : myLatlng,
						map : map,
						icon : image
					});
					BuildInfoWindow(results[0] , map, marker);
					map.panTo(myLatlng);
				} else {
					alert('No results found');
				}
			} else {
				alert('Geocoder failed due to: ' + status);
			}
		});
	}
}

function BuildInfoWindow(results , map, marker){
	if (newsType == WEATHER_TYPE)
		setMouseWeatherInfoWindow(results, map, marker);
	else if (newsType == POLLUTION_TYPE)
		setMousePollutionInfoWindow(results, map, marker);
	else
		setMouseInfoWindow(results, map, marker);
}

function buttonClickSearchLocation(place) {
	if (place != undefined) {
		map.setCenter(place.geometry.location);
		// set new marker
		marker = new google.maps.Marker({
			position : myLatlng,
			map : map,
			icon : image
		});
		BuildInfoWindow(place , map, marker);
		panToCurrentPosition(place.geometry.location, map);
	} else {
		alert("Geocode was not successful for the following reason: " + status);
	}
}


// sets the infowindow for results on specified map ,marker position
function setInfoWindow(results, map, marker) {
	var news_url = processLocation(results, map);
	fetchNews(news_url);
}

// sets the infowindow for results on specified map ,marker position
function setMouseInfoWindow(results, map, marker) {
	var news_url = processMouseLocation(results, map);
	fetchNews(news_url);
}

function GeoLocation(zip, city, county, state, country) {
	this.zip = zip;
	this.city = city;
	this.county = county;
	this.state = state;
	this.country = country;	
}

function buildGeoLocation(location) {
	var geoLocation = new Object();
	geoLocation.zip = getLocation(location, "postal_code");
	geoLocation.city = getLocation(location, "locality");

	if (typeof (geoLocation.city) == "undefined") {
		geoLocation.city = getLocation(location, "sublocality")
		if (typeof (geoLocation.city) == "undefined")
			geoLocation.city = getLocation(location, "colloquial_area")
		if (typeof (geoLocation.city) == "undefined")
			geoLocation.city = getLocation(location, "neighborhood")
		if (typeof (geoLocation.city) == "undefined")
			geoLocation.city = getLocation(location,
					"administrative_area_level_2")
	}
	geoLocation.county = getLocation(location, "administrative_area_level_2")
	geoLocation.state = getLocation(location, "administrative_area_level_1");
	geoLocation.country = getLocation(location, "country");
	return geoLocation;
}

// set location based on Location search
function processLocation(location, map) {
	var locationType;
	var geo_url;

	var geoLocation = buildGeoLocation(location);

	if (geoLocation.zip != null) {
		locationType = LOCATION_TYPE_ZIP;
		map.setZoom(ZOOM_LEVEL_ZIP);
		selectedLocation = geoLocation.zip;
	} else if (geoLocation.city != null) {
		locationType = LOCATION_TYPE_CITY;
		map.setZoom(ZOOM_LEVEL_CITY);
		selectedLocation = geoLocation.city;
	} else if (geoLocation.state != null) {
		locationType = LOCATION_TYPE_STATE;
		map.setZoom(ZOOM_LEVEL_STATE);
		selectedLocation = geoLocation.state;
	} else {
		locationType = LOCATION_TYPE_COUNTRY;
		map.setZoom(ZOOM_LEVEL_COUNTRY);
		selectedLocation = geoLocation.country;
	}
	geo_url = "&geo=" + addressBuilder(geoLocation, locationType);
	geo_url = geo_url.replace(/ /g, "%20");
	var base_url = geo_url;
	return base_url;
}


function setMouseWeatherInfoWindow(results, map, marker) {
	var weather_news_url = processMouseWeatherLocation(results, map);
	fetchWeatherNews(weather_news_url);
}

function setWeatherInfoWindow(results, map, marker) {
	var weather_address = processWeatherLocation(results, map);
	fetchWeatherNews(weather_address);
}

function processWeatherLocation(location, map) {
	map.setZoom(ZOOM_LEVEL_CITY);
	var geo = getFormattedLocation(location);
	geo = geo.replace(/ /g, "%20");
	return geo;
}

function processMouseWeatherLocation(location, map) {
	var address = getFormattedLocation(location);
	return address;
}

function setMousePollutionInfoWindow(results, map, marker) {

}

function setMousePollutionInfoWindow(results, map, marker) {

}

// location types can be
// "administrative_area_level_1" = state
// "administrative_area_level_2" == county / district
// "locality" == city
// "neighborhood" == city
// "sublocality" == city
// "colloquial_area" =city
// "country" == country
// "street_number" == street number
// "route" == street / route name
// "postal_code" == zip / postal code
// search location from address

function getLocation(location, type) {
	for (var i = 0; i < location.address_components.length; i++) {
		var location_type = location.address_components[i].types[0];
		if (location_type == type) {
			return location.address_components[i].long_name;
		}
	}
}

// get location with accordance to zoom level set
function processMouseLocation(location, map) {
	var base_url;
	var geo_url;
	var geoLocation = buildGeoLocation(location);
	if (map.getZoom() >= ZOOM_LEVEL_ZIP) {
		if (geoLocation.zip != null) {
			locationType = LOCATION_TYPE_ZIP;
			selectedLocation = geoLocation.zip;
		} else if (geoLocation.city != null) {
			locationType = LOCATION_TYPE_CITY;
			selectedLocation = geoLocation.city;
		} else if (geoLocation.state != null) {
			locationType = LOCATION_TYPE_STATE;
			selectedLocation = geoLocation.state;
		} else if (geoLocation.country != null) {
			locationType = LOCATION_TYPE_COUNTRY;
			selectedLocation = geoLocation.country;
		}
	} else if (map.getZoom() <= ZOOM_LEVEL_ZIP
			&& map.getZoom() >= ZOOM_LEVEL_CITY) {
		if (geoLocation.city != null) {
			locationType = LOCATION_TYPE_CITY;
			selectedLocation = geoLocation.city;
		} else if (geoLocation.state != null) {
			locationType = LOCATION_TYPE_STATE;
			selectedLocation = geoLocation.state;
		} else if (geoLocation.country != null) {
			locationType = LOCATION_TYPE_COUNTRY;
			selectedLocation = geoLocation.country;
		}
	} else if (map.getZoom() <= ZOOM_LEVEL_CITY
			&& map.getZoom() >= ZOOM_LEVEL_STATE) {
		if (geoLocation.state != null) {
			locationType = LOCATION_TYPE_STATE;
			selectedLocation = geoLocation.state;
		} else if (geoLocation.country != null) {
			locationType = LOCATION_TYPE_COUNTRY;
			selectedLocation = geoLocation.country;
		}
	} else if (map.getZoom() <= ZOOM_LEVEL_STATE
			&& map.getZoom() >= ZOOM_LEVEL_COUNTRY) {
		if (geoLocation.country != null) {
			locationType = LOCATION_TYPE_COUNTRY;
			selectedLocation = geoLocation.country;
		}
	}

	geo_url = "&geo=" + addressBuilder(geoLocation, locationType);
	base_url = geo_url;
	return base_url;
}


function addressBuilder(geoLocation, locationType) {
	var geo_url;

	if (geoLocation.zip != null || typeof (geoLocation.zip) != "undefined") {
		if (locationType == LOCATION_TYPE_ZIP)
			geo_url = geoLocation.zip.concat(' ').concat(geoLocation.city)
					.concat(' ').concat(geoLocation.state).concat(' ').concat(
							geoLocation.country);
		else if (locationType == LOCATION_TYPE_CITY)
			geo_url = geoLocation.city.concat(' ').concat(geoLocation.state)
					.concat(' ').concat(geoLocation.country);
		else if (locationType == LOCATION_TYPE_STATE)
			geo_url = geoLocation.state.concat(' ').concat(geoLocation.country);
		else
			geo_url = geoLocation.country;
	} else if (geoLocation.city != null
			|| typeof (geoLocation.city) != "undefined") {
		if (locationType == LOCATION_TYPE_CITY)
			geo_url = geoLocation.city.concat(' ').concat(geoLocation.state)
					.concat(' ').concat(geoLocation.country);
		else if (locationType == LOCATION_TYPE_STATE)
			geo_url = geoLocation.state.concat(' ').concat(geoLocation.country);
		else
			geo_url = geoLocation.country;
	} else if (geoLocation.state != null
			|| typeof (geoLocation.state) != "undefined") {
		if (locationType == LOCATION_TYPE_STATE)
			geo_url = geoLocation.state.concat(' ').concat(geoLocation.country);
		else
			geo_url = geoLocation.country;
	} else {
		geo_url = geoLocation.country;
	}
	selectedCountry = geoLocation.country;
	return geo_url;
}


function getFormattedLocation(location) {
	var geoLocation = buildGeoLocation(location);
	if (typeof (geoLocation.city) != "undefined")
		return geoLocation.city.concat(' ').concat(geoLocation.state).concat(
				' ').concat(geoLocation.country);
	else
		return geoLocation.state.concat(' ').concat(geoLocation.country);
}

function fetchNews(queryString) {
	// order latest news with scoring keyword
	final_url = queryString;
	var rest_url = GOOGLE_NEWS_WEBSERVICE + queryString;
	rest_url = AddCountryPresent(rest_url);
	rest_url += "&scoring=n";
	rest_url = rest_url.replace(/ /g, "%20");
	var client = createClientRequest(rest_url);

	if (client != null) {
		client.followsRedirect = false;
		client.onreadystatechange = function() {
			if (client.readyState == 4) {
				if (client.status == 200) {
					var response = eval('(' + client.responseText + ')');
					var html = generateNewsPopup(response);
					infoWindow.setContent(html);
					infoWindow.open(map, marker);
				} else {
					var errorHtml = generateErrorHtml(null);
					infoWindow.setContent(html);
					infoWindow.open(map, marker);
				}
			}
		};
		client.send();
	}
}

function fetchWeatherNews(weather_address) {
	// order latest news with scoring keyword
	var rest_url = YAHOO_WEATHER_WEBSERVICE;
	rest_url += weather_address;
	rest_url = rest_url.replace(/ /g, "%20");
	var client = createClientRequest(rest_url);
	if (client != null) {
		client.followsRedirect = false;
		client.onreadystatechange = function() {
			if (client.readyState == 4) {
				if (client.status == 200) {
					var response = eval('(' + client.responseText + ')');
					var html = generateWeatherPopup(response);
					infoWindow.setContent(html);
					infoWindow.open(map, marker);
				} else {
					var errorHtml = generateErrorHtml(null);
					infoWindow.setContent(html);
					infoWindow.open(map, marker);
				}
			}
		};
		client.send();
	}
}


function createClientRequest(rest_url) {
	var xhr = new XMLHttpRequest();
	var method = 'GET';
	if ("withCredentials" in xhr) {
		xhr.open(method, rest_url, true);
		xhr.setRequestHeader('Content-Type',
				'application/x-www-form-urlencoded');
		xhr.setRequestHeader('Cache-Control', 'max-age=3600');
		xhr.setRequestHeader('Access-Control-Allow-Methods', method);
		xhr.setRequestHeader('Access-Control-Allow-Credentials', 'false');
		xhr.setRequestHeader('Access-Control-Allow-Headers',
				'Origin, X-Requested-With, Accept');
	} else if (typeof XDomainRequest != "undefined") {
		// Otherwise, check if XDomainRequest.
		// XDomainRequest only exists in IE, and is IE's way of making CORS
		// requests.
		xhr = new XDomainRequest();
		xhr.open(method, rest_url);
	} else {
		// Otherwise, CORS is not supported by the browser.
		xhr = null;
	}
	return xhr;
}

// generate html for infowindow from feed call back
function generateErrorHtml(result) {
	var html = '<!DOCTYPE html><html><head><body>Sorry !! No News Available';
	html += '</body></html>';
	return html;
}

var typingTimer;
var selectedOption = 'g';

// Detect keystroke and only execute after the user has finish typing
function delayExecute() {
	clearTimeout(typingTimer);
	typingTimer = setTimeout(function() {
		var url = final_url
		keyword = document.getElementById('keyword');
		if (IsKeywordPresent()) {
			var queryString = "&q=" + keyword.value;
			var regex = new RegExp(/(q=)[^\&]+/);
			if (url.match(regex))
				url = url.replace(regex, '$1' + keyword.value);
			else
				url += queryString;
		}

		topic = document.getElementById('topic');
		if (IsTopicPresent()) {
			selectedOption = topic.value;
			if (selectedOption != 'g') {
				var queryString = "&topic=" + selectedOption;
				var regex = new RegExp(/(topic=)[^\&]+/);
				if (url.match(regex))
					url = url.replace(regex, '$1' + selectedOption);
				else
					url += queryString;
			}
		}
		fetchNews(url);
	}, 1000);
	return true;
}

function IsKeywordPresent() {
	if (typeof (keyword) != "undefined" && keyword.value.length > 0)
		return true;
	else
		return false
}

function IsTopicPresent() {
	if (typeof (topic) != "undefined")
		return true;
	else
		return false;
}

function AddCountryPresent(resturl) {
	var country;
	if (typeof (selectedCountry) != "undefined" || selectedCountry != null) {
		country = selectedCountry;
	} else
		country = "United States";

	var queryString = "&country=" + country;
	var regex = new RegExp(/(country=)[^\&]+/);
	if (resturl.match(regex))
		return resturl = resturl.replace(regex, '$1' + country);
	else
		return resturl += queryString;
}

function StripURL(link) {
	var startIndex = link.indexOf("&url=");
	return link.substring(startIndex, link.length);
}

/*
 * geodecode error
 */
function error(err) {
	console.warn('ERROR(' + err.code + '): ' + err.message);
};
/*
 * HTML generator
 */
function generateNewsPopup(result) {
	var copyright = result.copyright;
	var language = result.language;
	var title = result.title.substr(0, result.title.indexOf('-') - 1);
	var html;
	if (result.messages.length > 0) {
		html = '<!DOCTYPE html><html><head><link rel="stylesheet" href="css/style.css" />'
				+ '</head><div id="info_window"><body><table class="newstable">';
		html += '<tr><td><h1 class="location">'
				+ selectedLocation
				+ '</h1>'
				+ '<input type="text" id="keyword" placeholder="Enter Keyword" onkeypress="return delayExecute()" class="controls"/>';
		html += '<select id="topic" class="controls" onchange="return delayExecute()">'
				+ '<option value="g">General</option>'
				+ '<option value="b">Business</option>'
				+ '<option value="h">Headlines</option>'
				+ '<option value="t">Science and Technology</option>'
				+ '<option value="el">Elections</option>'
				+ '<option value="p">Politics</option>'
				+ '<option value="e">Entertainment</option>'
				+ '<option value="s">Sports</option>'
				+ '<option value="m">Health</option>' + '</select></td></tr>';
		// Loop through our items​​​​​​​​​​
		for (var i = 0; i < result.messages.length; i++) {
			var item = result.messages[i];
			var headline = item.headlines;
			var source = item.publisher;
			var link = item.link;
			var publicationDate = item.pubdate;
			var guid = item.guid;
			var description = item.description;
			var imgSrc = item.imgSource;

			html += '<tr><td><table >';
			html += '<tr><td class="headline"><h2>' + headline
					+ '</h2></td></tr><tr><td><span class="source">&nbsp;'
					+ source + '</span>';
			html += '<span class="pubDate">&nbsp;' + publicationDate
					+ '&nbsp;</span>' + '</td></tr>';
			html += '<tr><td><p>'
					+ '<img class="thumbnail" src="'
					+ imgSrc
					+ '">'
					+ description
					+ '<a class="fa solo fa-twitter"'
					+ 'href="https://twitter.com/intent/tweet?text='
					+ StripURL(link)
					+ '"'
					+ 'data-size="large" '
					+ 'data-via="worldnewsonmap"'
					+ 'target="_blank">&nbsp;</a>'
					+ '<a class="readmorelink" href='
					+ link
					+ ' target="_blank">&nbsp;Read More&nbsp;&#x2794;</a></p></td></tr>';
			html += '</table></td></tr>';
		}
		html += '<tr><td style="padding-left: 450px;">';
		html += '<a href="https://news.google.com/">Powered by <img src="https://ssl.gstatic.com/news-static/img/logo/en_us/news.gif"></a></td></tr>';
		html += '</table></div></body></html>';
	} else {
		html = generateErrorHtml(result);
	}
	return html;
}


function generateWeatherPopup(result) {
	var html = '<!DOCTYPE html><html><head><link rel="stylesheet" href="css/style.css" />'
			+ '</head><body><div id="info_window_weather">'
			+ '<table class="weathertable">'
			+ '<tr>'
			+ '<td colspan="4" >'
			+ '<h1 class="header">'
			+ result.title
			+ '</h1></td>'
			+ '</tr>'
			+ '<tr style="border-bottom-width: 1px;">'
			+ '<td class="wpubDate">'
			+ result.pubDate
			+ '</td>'
			+ '<td>&nbsp;</td>'
			+ '<td>&nbsp;</td>'
			+ '</tr>'
			+ '<tr>'
			+ '<td>&nbsp;</td>'
			+ '<td class="topic" colspan="2" style="border-bottom-width: 1px;">Wind</td></tr>'
			+ '<tr>'
			+ '<td>&nbsp;</td>'
			+ '<td>Chill</td>'
			+ '<td>'
			+ result.wind.chill
			+ ' '
			+ result.units.speed
			+ '</td>'
			+ '</tr>'
			+ '<tr>'
			+ '<td>&nbsp;</td>'
			+ '<td>Direction</td>'
			+ '<td>'
			+ result.wind.direction
			+ ' '
			+ result.units.speed
			+ '</td>'
			+ '</tr>'
			+ '<tr>'
			+ '<td>&nbsp;</td>'
			+ '<td class="topic" colspan="2" style="border-bottom-width: 1px;">Atmosphere</td>'
			+ '</tr>'
			+ '<tr>'
			+ '<td rowspan="4">'
			+ '<span class="current"><img src="'
			+ result.imageSource
			+ '"/>'
			+ result.currentTemp
			+ '&deg;F</span></td>'
			+ '<td>Humidity</td>'
			+ '<td>'
			+ result.atmosphere.humidity
			+ ' %</td>'
			+ '</tr>'
			+ '<tr>'
			+ '<td>Visibility</td>'
			+ '<td>'
			+ result.atmosphere.visibility
			+ ' '
			+ result.units.distance
			+ '</td>'
			+ '</tr>'
			+ '<tr><td class="topic" colspan="2" style="border-bottom-width: 1px;">Astronomy</td></tr>'
			+ '<tr>'
			+ '<td>Sunrise</td>'
			+ '<td>'
			+ result.astronomy.sunrise
			+ '</td></tr>'
			+ '<tr>'
			+ '<td><span class="forecast">Min :</span>'
			+ result.forecasts.low
			+ '&deg;F&nbsp;'
			+ '<span class="forecast">Max :</span>'
			+ result.forecasts.high
			+ '&deg;F</td>'
			+ '<td>Sunset</td>'
			+ '<td>'
			+ result.astronomy.sunset
			+ '</td>'
			+ '</tr>'
			+ '<tr>'
			+ '<td colspan="4" style="height: 50px"><img alt="Yahoo News Weather" height="30" '
			+ 'src="http://l.yimg.com/a/i/brand/purplelogo//uh/us/news-wea.gif" class="ywimage"></td>'
			+ '</tr>' + '</table></div></body></html>';
	return html
}