
function showrecentposts(json) {
  var entry, posttitle, posturl, 
      i, k, postdate, postauthor,
      postcontent, 
      re = /<\S[^>]*>/g,
      numposts = 50,
      numchars = 100;

  // start a loop
  // in this loop we get the entry from the feed and parse it
  for (i = 0; i < numposts; i++) {
    // get entry i from feed
    entry = json.feed.entry[i];
    
    // get the posttitle
    posttitle = entry.title.$t;
    
    // get the post url
    // check all links for the link with rel = alternate
    if (i === json.feed.entry.length) {
      break;
    }
    for (k = 0; k < entry.link.length; k++) {
      if (entry.link[k].rel === 'alternate') {
        posturl = entry.link[k].href;
        break;
      }
    }
    
    // get the postdate, take only the first 10 characters
    postdate = entry.published.$t.substring(0,10);
    
    // get the post author
    postauthor = entry.author[0].name.$t;
    
    // get the postcontent
    // if the Blogger-feed is set to FULL, then the content is in the content-field
    // if the Blogger-feed is set to SHORT, then the content is in the summary-field
    if ("content" in entry) {
      postcontent = entry.content.$t;
    } else if ("summary" in entry) {
      postcontent = entry.summary.$t;
    } else {
      postcontent = "";
    }
    
    // strip off all html-tags
    postcontent = postcontent.replace(re, "");
    
    // reduce postcontent to numchar characters
    if (postcontent.length > numchars) {
      postcontent = postcontent.substring(0,numchars);
    }
    
    // display the results
    //document.write('<a href="'+ posturl + '">' +posturl+'</a><br>');
    $("#jay").append('<a href="'+ posturl + '">' +posturl+'</a><br></br>');
    console.log('<a href="'+ posturl + '">' +posturl+'</a><br></br>');
  }
}
