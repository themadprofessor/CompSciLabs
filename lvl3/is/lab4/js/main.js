var mymap;
$(document).ready(function() {
	mymap = L.map('mapid').setView([51.505, -0.09], 13);
	L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token={accessToken}', {
		attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors, <a href="https://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, Imagery © <a href="https://www.mapbox.com/">Mapbox</a>',
		maxZoom: 18,
		id: 'mapbox.streets',
		accessToken: 'pk.eyJ1IjoiZ29iYmxlY29jayIsImEiOiJjam41emt3czY1c21uM3BueHFyNHo5cngwIn0.2tWUcXumB5dUBT68Tm_VCw'
	}).addTo(mymap);
});

$('#file-input').change(function(e) {
	var file = e.target.files[0];
	if (!file) {
		alert("Not a file");
		return;
	}

	var reader = new FileReader();
	reader.onload = function(e) {
		var content = e.target.result;
		handleData(content);
	};
	reader.readAsText(file);
});

function handleData(data) {
	xmlDoc = $.parseXML(data);
	$xml = $(xmlDoc);
	$('#activity-para').text(($xml.find("trk > name").text()));

	latlongs = [];
	for (pt in $xml.find("trk > trkseg > trkpt")) {
		console.log(pt.outerHTML);
		latlongs.push([parseFloat($(pt).attr("lat")), parseFloat($(pt).attr("lon"))]);
	}
	var line = L.polyline(latlongs, {color: 'black'}).addTo(mymap);
	mymap.fitBounds(line.getBounds());
}
