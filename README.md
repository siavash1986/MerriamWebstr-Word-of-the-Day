# Merriam Webster Word of the Day Archive

# Build:
In order to build the application you need to add your own application.properties with database parameters set.

# Endpoints:
  <code>GET localhost:8080/word</code>
  
|Parameter|Optional|Description|
|---------|--------|-----------|
|date|Yes|ISO-8601 extended date format ex: 2011-12-03|

<code>GET localhost:8080/word/flat</code>

|Parameter|Optional|Description|
|---------|--------|-----------|
|date|Yes|ISO-8601 extended date format ex: 2011-12-03|


<code>PUT localhost:8080/word</code>

parse and insert the given <a href="https://www.merriam-webster.com/word-of-the-day">word of the day</a> into database.
date parameter is default to server current date is not provided.

|Parameter|Optional|Description|
|---------|--------|-----------|
|date|Yes|ISO-8601 extended date format ex: 2011-12-03|


<code>GET localhost:8080/batch/words</code>

|Parameter|Optional|Description|
|---------|--------|-----------|
|dateBegin|Yes|ISO-8601 extended date format ex: 2011-12-03|
|dateEnd|Yes|ISO-8601 extended date format ex: 2011-12-03|

<code>GET localhost:8080/batch/batchwords</code>

|Parameter|Optional|Description|
|---------|--------|-----------|
|dateBegin|Yes|ISO-8601 extended date format ex: 2011-12-03|
|dateEnd|Yes|ISO-8601 extended date format ex: 2011-12-03|



return object:
<code>
{
    "date": "2018-05-11",
    "title": "otiose",
    "attribute": "adjective",
    "syllables": "OH-shee-ohss",
    "definition": [
        "1 : producing no useful result : futile",
        "2 : being at leisure : idle",
        "3 : lacking use or effect : functionless"
    ],
    "examples": [
        "\"Ever since I was seven years old, I have been collecting books and articles on the Great Flood, hoping to write the full account myself. David McCullough's The Johnstown Flood (1968) was so brilliant that it rendered my own ambition otiose.\" — Michael Novak, National Review, 4 June 2014",
        "\"He did not have the patience for otiose people like Gibson, whom he put in the same category as those rude reporters who continued to pester him daily with inane queries and ridiculous suggestions.\" — Godfrey Wray, Beyond Revenge, 2008"
    ],
    "didYouKnow": "Otiose was first used in English in the late-18th century to describe things producing no useful result. By mid-19th century, it was being used in keeping with its Latin source otiosus, meaning \"at leisure.\" There is also the noun form otiosity, which predates otiose by approximately three centuries. That noun is rarely found in writing today, but it makes an appearance on the occasional spelling bee word list.",
    "podcastUrl": "https://s3.us-east-2.amazonaws.com/mwwottd/words/wd20180511.mp3",
    "imageUrl": null
}
</code>

