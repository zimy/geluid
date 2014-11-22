var req = new XMLHttpRequest();
var typeOfTab = "";
var hostprefix = "http://localhost:8080";

window.onload = function(){
	//document.getElementById('audioPlayer').src="sound.mp3";
}

function refreshAllTabs(){
	var songs = document.getElementById('songs');
	var authors = document.getElementById('authors');
	var albums = document.getElementById('albums');
	var genres = document.getElementById('genres');
	songs.style.backgroundColor = authors.style.backgroundColor =
	albums.style.backgroundColor = genres.style.backgroundColor = "#F9F9F9";
	songs.style.borderRadius = authors.style.borderRadius =
	albums.style.borderRadius = genres.style.borderRadius = "5px";
	songs.style.borderBottom = authors.style.borderBottom =
	albums.style.borderBottom = genres.style.borderBottom = "2px #444444 solid";
}

function getGroupBySongId(child){
	var id = child.parentNode.parentNode.id;
	typeOfTab = "song";
 	$.ajax({
		url: hostprefix + "/songs/author/" + id
	}).then(function(data){
		checkTab("songs");
		fillLibrary(data,typeOfTab);
	});
}
function getAlbumBySongId(child){
	var id = child.parentNode.parentNode.id;
	typeOfTab = "song";
 	$.ajax({
		url: hostprefix + "/songs/album/" + id
	}).then(function(data){
		checkTab("songs");
		fillLibrary(data,typeOfTab);
	});
}
function getGenreBySongId(child){
	var id = child.parentNode.parentNode.id;
	typeOfTab = "song";
 	$.ajax({
		url: hostprefix + "/songs/genre/" + id
	}).then(function(data){
		checkTab("songs");
		fillLibrary(data,typeOfTab);
	});
}
function getSongsByAuthorId(child){
	var id = child.parentNode.parentNode.id;
	typeOfTab = "song";
 	$.ajax({
		url: hostprefix + "/songs/author/all/" + id
	}).then(function(data){
		checkTab("songs");
		fillLibrary(data,typeOfTab);
	});
}
function getAlbumsByAuthorId(child){
	var id = child.parentNode.parentNode.id;
	typeOfTab = "album";
 	$.ajax({
		url: hostprefix + "/albums/author/all" + id
	}).then(function(data){
		checkTab("albums");
		fillLibrary(data,typeOfTab);
	});
}
function getSongsByAlbumId(child){
	var id = child.parentNode.parentNode.id;
	typeOfTab = "song";
 	$.ajax({
		url: hostprefix + "/songs/album/all/" + id
	}).then(function(data){
		checkTab("songs");
		fillLibrary(data,typeOfTab);
	});
}
function getGroupByAlbumId(child){
	var id = child.parentNode.parentNode.id;
	typeOfTab = "group";
 	$.ajax({
		url: hostprefix + "/authors/album/" + id
	}).then(function(data){
		checkTab("groups");
		fillLibrary(data,typeOfTab);
	});
}
function getSongsByGenreId(child){
	var id = child.parentNode.parentNode.id;
	typeOfTab = "song";
 	$.ajax({
		url: hostprefix + "/songs/genre/all/" + id
	}).then(function(data){
		checkTab("songs");
		fillLibrary(data,typeOfTab);
	});
}

function getAllSongs(){
	typeOfTab = "song";
	$.ajax({
		url: hostprefix + "/songs"
	}).then(function(data){
		fillLibrary(data,typeOfTab);
	});
}
function getAllAuthors(){
	typeOfTab = "author";
	$.ajax({
		url: hostprefix + "/authors"
	}).then(function(data){
		fillLibrary(data,typeOfTab);
	});
}
function getAllAlbums(){
	typeOfTab = "album";
	$.ajax({
		url: hostprefix + "/albums"
	}).then(function(data){
		fillLibrary(data,typeOfTab);
	});
}
function getAllGenres(){
	typeOfTab = "genre";
	$.ajax({
		url: hostprefix + "/genres"
	}).then(function(data){
		fillLibrary(data,typeOfTab);
	});
}

function fillPlaylist(list){
	var length;
	if (typeOfTab=="song") length = 1;
	else length = list.length;
	
	var fragment = document.createDocumentFragment();
	for (var i = 0; i < length; ++i){
		var item;
		if (typeOfTab=="song") item = list;
		else item = list[i];
		
		var id=item['id'],
		title = '<p class="line_name">'+item['name']+'</p>',
		time = "<p class='line_time'>" + (item['length'] / 60 | 0) + ":" + (item['length'] % 60) + "</p>";
		
		var newDiv = document.createElement('div');
		newDiv.id = id; newDiv.className = "library_line";
		newDiv.innerHTML = '<img src="images/playButton.png" onclick="playSong(this)" alt="play music" class="playButton"/>'+
			time + title +
			'<img src="images/deleteButton.png" alt="delete song" class="deleteButton" onclick="deleteSongFromPlaylist(this)"/>';
		fragment.appendChild(newDiv);
	}
	document.getElementsByClassName('playlist_content')[0].appendChild(fragment);
}

function fillLibrary(list, type){
	document.getElementsByClassName('library_content')[0].innerHTML = "";
	var fragment = document.createDocumentFragment();
	for (var i = 0; i < list.length; ++i){
		var item = list[i];
		
		var id=item['id'], title, time="", info1="", info2="", info3="";
		title = '<p class="line_name">'+item['name']+'</p>';
		
		switch(type){
			case "song":
				time = "<p class='line_time'>" + (item['length'] / 60 | 0) + ":" + (item['length'] % 60) + "</p>";
				info1 = '<div class="infoDiv"><img src="images/infoButtonGroup.png" onclick="getGroupBySongId(this)" title="Group: '+ item['authorValue'] +'" alt="group"/></div>';
				info2 = '<div class="infoDiv"><img src="images/infoButtonAlbum.png" onclick="getAlbumBySongId(this)" title="Album: '+ item['albumValue'] +'" alt="album"/></div>';
				info3 = '<div class="infoDiv"><img src="images/infoButtonGenre.png" onclick="getGenreBySongId(this)" title="Genre: '+ item['genreValue'] +'" alt="genre"/></div>';
				break;
			case "author":
				info1 = '<div class="infoDiv"><img src="images/infoButtonSong.png" onclick="getSongsByAuthorId(this)" title="Songs count: '+ item['songsValue'] +'" alt="songs"/></div>';
				info2 = '<div class="infoDiv"><img src="images/infoButtonAlbum.png" onclick="getAlbumsByAuthorId(this)" title="Albums count: '+ item['albumsValue'] +'" alt="albums"/></div>';
				break;	
			case "album":
				info1 = '<div class="infoDiv"><img src="images/infoButtonSong.png" onclick="getSongsByAlbumId(this)" title="Songs count: '+ item['songsValue'] +'" alt="songs"/></div>';
				info2 = '<div class="infoDiv"><img src="images/infoButtonGroup.png" onclick="getGroupByAlbumId(this)" title="Group: '+ item['authorValue'] + '" alt="group"/></div>';
				break;
			case "genre":
				info1 = '<div class="infoDiv"><img src="images/infoButtonSong.png" onclick="getSongsByGenreId(this)" title="Songs count: '+ item['songsValue'] +'" alt="songs"/></div>';
				break;	
		}
		
		
		var newDiv = document.createElement('div');
		newDiv.id = id; newDiv.className = "library_line";
		newDiv.innerHTML = 	'<img src="images/playButton.png" onclick="addToPlaylist(this, true)" alt="play music" class="playButton"/>'+
							'<img src="images/addButton.png" onclick="addToPlaylist(this, false)" alt="add to playlist" class="addButton"/>'+
							time + title + info3 + info2 + info1;
		fragment.appendChild(newDiv);
	}
	document.getElementsByClassName('library_content')[0].appendChild(fragment);
	
}

function playSong(child){
	var id = child.parentNode.id;
	var player = document.getElementById('audioPlayer');
  	var url = hostprefix + "/tracks/" + id;
	player.src = url;
	player.play();
}



function addToPlaylist(child, flag){
	//flag == false - просто добавляем в плейлист песни
	//flag == true - очищаем плейлист, добавляем песни, запускаем.
	if (flag == true)
		document.getElementsByClassName('playlist_content')[0].innerHTML = "";

	var myUrl;
	switch (typeOfTab){
		case "song": myUrl="/songs/"; break;
		case "author": myUrl="/songs/author/all/"; break;
		case "album": myUrl="/songs/album/all/"; break;
		case "genre": myUrl="/songs/genre/all/"; break;
	}
	var id = child.parentNode.id;
	$.ajax({
		url: hostprefix + myUrl + id
	}).then(function (data){
		fillPlaylist(data);
	});
}
function deleteSongFromPlaylist(child){
	var lineForDel = child.parentNode;
	lineForDel.parentNode.removeChild(lineForDel);
}

function onTabClick(tab){
	refreshAllTabs();
	tab.style.backgroundColor="#dddddd";
	tab.style.borderBottom="none";
	tab.style.borderBottomRightRadius="0px";
	tab.style.borderBottomLeftRadius="0px";
	switch(tab.id){
		case "songs":
			getAllSongs();
			break;
		case "authors":
			getAllAuthors();
			break;
		case "albums":
			getAllAlbums();
			break;
		case "genres":
			getAllGenres();
			break;
	}
}
function checkTab(tabId){
	refreshAllTabs();
	var tab = document.getElementById(tabId);
	tab.style.backgroundColor="#dddddd";
	tab.style.borderBottom="none";
	tab.style.borderBottomRightRadius="0px";
	tab.style.borderBottomLeftRadius="0px";
}