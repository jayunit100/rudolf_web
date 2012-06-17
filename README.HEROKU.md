-- Deployment to heroku, notes -- 

Auto deployment to heroku is done basde on the following parameters. 
You will need these to deploy to the heroku site  


  heroku config:add GITHUB_USERNAME=jayunit100
  heroku config:add HEROKU_API_KEY=(get this from jay) 
  heroku config:add GITHUB_REPO=https://github.com/jayunit100/rudolf_web
  heroku config:add HEROKU_REPO=git@heroku.com:rudolfcode.git
  heroku config:add HEROKU_USERNAME=jayunit100


-- Regarding the auto deployer : where is it ? -- 

The auto deployer is ephemeral, and you can set it up on any machine by simply pulling it from github (the project is called github-heroku-pusher). 

Arbitrarily, it has been created as a heroku server application according to the README file here : 

https://github.com/ajlai/github-heroku-pusher

With the following specifications : 

jays-MacBook-Pro:github-heroku-pusher jayunit100$ heroku info

young-waterfall-6861

Database Size: (empty)

Git URL:       git@heroku.com:young-waterfall-6861.git

Owner Email:   jayunit100@gmail.com

Repo Size:     1M

Slug Size:     1M

Stack:         cedar

Web URL:       http://young-waterfall-6861.herokuapp.com/


-- These might change in the future, but for now auto deployer runs on young-waterfall-6861.herokuapp.com ! -- 
