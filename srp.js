var numposts=50;
var numchars=100;
function showrecentposts(json) {
  // start a loop
  // in this loop we get the entry from the feed and parse it
  for (var i = 0; i < numposts; i++) {
    // get entry i from feed
    var entry = json.feed.entry[i];
    // get the posttitle
    var posttitle = entry.title.$t;
    // get the post url
    // check all links for the link with rel = alternate
    var posturl;
    if (i == json.feed.entry.length) break;
    for (var k = 0; k < entry.link.length; k++) {
      if (entry.link[k].rel == 'alternate') {
        posturl = entry.link[k].href;
        break;
      }
    }
    // get the postdate, take only the first 10 characters
    var postdate = entry.published.$t.substring(0,10);
    // get the post author
    var postauthor = entry.author[0].name.$t;
    // get the postcontent
    // if the Blogger-feed is set to FULL, then the content is in the content-field
    // if the Blogger-feed is set to SHORT, then the content is in the summary-field
    if ("content" in entry) {
      var postcontent = entry.content.$t;}
    else
    if ("summary" in entry) {
      var postcontent = entry.summary.$t;}
    else var postcontent = "";
    // strip off all html-tags
    var re = /<\S[^>]*>/g;
    postcontent = postcontent.replace(re, "");
    // reduce postcontent to numchar characters
    if (postcontent.length > numchars) postcontent = postcontent.substring(0,numchars);
    // display the results
    //document.write('<a href="'+ posturl + '">' +posturl+'</a><br>');
    $("#jay").append('<a href="'+ posturl + '">' +posturl+'</a><br></br>');
    console.log('<a href="'+ posturl + '">' +posturl+'</a><br></br>');
  }
}
