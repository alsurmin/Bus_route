
var singleUploadForm = document.querySelector('#singleUploadForm');
var singleFileUploadInput = document.querySelector('#singleFileUploadInput');
var singleFileUploadError = document.querySelector('#singleFileUploadError');
var singleFileUploadSuccess = document.querySelector('#singleFileUploadSuccess');

var startRouteInput = document.querySelector('#startRouteInput');
var finishRouteInput = document.querySelector('#finishRouteInput');
var routeFoundResult  = document.querySelector('#routeFoundResult');

function uploadSingleFile(file, from, to) {
    var formData = new FormData();
    formData.append("file", file);
    formData.append("from", from);
    formData.append("to", to);

    var xhr = new XMLHttpRequest();
    xhr.open("POST", "/uploadFile");

    xhr.onload = function() {
        console.log(xhr.responseText);
        var response = JSON.parse(xhr.responseText);
        if(xhr.status == 200) {
            if (response.routeFound != "-1") {
                routeFoundResult.innerHTML = "<p>Подходящий маршрут: " + response.routeFound + "</p>";
            }else {
                routeFoundResult.innerHTML = "<p>Маршрут не найден.</p>";
            }
            singleFileUploadError.style.display = "none";
            singleFileUploadSuccess.innerHTML = "<p>Ссылка на файл маршрута: <a href='" + response.fileDownloadUri + "' target='_blank'>" + response.fileDownloadUri + "</a></p>";
            singleFileUploadSuccess.style.display = "block";
        } else {
            singleFileUploadSuccess.style.display = "none";
            singleFileUploadError.innerHTML = (response && response.message) || "Some Error Occurred";
        }
    }

    xhr.send(formData);
}

singleUploadForm.addEventListener('submit', function(event){
    var files = singleFileUploadInput.files;
    var to = finishRouteInput.value;
    var from = startRouteInput.value;

    if(files.length === 0) {
        singleFileUploadError.innerHTML = "Please select a file";
        singleFileUploadError.style.display = "block";
    }
    uploadSingleFile(files[0], from, to);
    event.preventDefault();
}, true);


