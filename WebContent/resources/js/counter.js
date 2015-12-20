$(document).ready(function() {
	var per = 0;
    progress(per);
    time = setTimeout(function(){ animate(per); }, 1000);
    setTimeout(function(){ $( "#progressbar" ).hide(); }, 5000);

})


function animate(per){

    clearTimeout(time);
    per = per+20;
    progress(per);

    time = setTimeout(function(){ animate(per); }, 1000);
}

function progress(per){
    $( "#progressbar" ).progressbar({
        value: per
    });        
};