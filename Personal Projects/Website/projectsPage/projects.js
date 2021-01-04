//window.addEventListener('load', onStart());
//window.onload = onStart();

//Do some work here to initialize fields as hidden
//Currently onStart() doesn't work. temp is null
function onStart() {
    console.log("here");
    const beginningId = ["one", "two", "three", "four"];
    let i;
    for (i = 0; i < beginningId.length; i++) {

        let temp = document.getElementById(beginningId + "CollapsibleContainer");

        temp.style.display = "none";
    }
}

function clicked(id) {
    var x = document.getElementById(id + "Container");

    if (x.style.display === "none") {
        x.style.display = "block";
    } else {
        x.style.display = "none";
    }
}