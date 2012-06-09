Basic site design for blog (and tools):

 1. "general" content -- same for each blog article
  - header
  - body header
    - list of articles with links
  - body footer
    - "page created by …"
  
 2. article-"specific" content -- different for each article
  - extract article name from url
  - lookup article in meta.yaml to find metadata
  - read article file

 3. assemble content from 1 and 2
  - output as html


Inputs to blog:

 case 1:  rudolf/blog/
  - just needs the "general" content

 case 2:  rudolf/blog/name
  - ??


Testing plan:

 - correctly find and parse meta.yaml file(s)
   - verify contents
 - can find all files listed in meta.yaml(s)
 - header/footer/body-header are good html ??
 - ??
