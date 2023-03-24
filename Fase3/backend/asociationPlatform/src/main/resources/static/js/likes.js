//Calculates bar widths
function calculateBar(){
    var likes = document.getElementById('likeValue').value;
    var dislikes = document.getElementById('dislikeValue').value;
	var total= parseInt(likes)+parseInt(dislikes);
    //Simple math to calculate percentages
	var percentageLikes=(likes/total)*100;
	var percentageDislikes=(dislikes/total)*100;

    //We need to apply the widths to our elements
    
	document.getElementById('likeArea').style.width=percentageLikes.toString()+"%";
	document.getElementById('dislikeArea').style.width=percentageDislikes.toString()+"%";

}

calculateBar();