# jukebox

JukeBox application is intended to maintain a catalog of music albums by various musicians. The
application should also maintain information about musicians, music and the relationships between
musicians and music (i.e, who has sung/played which album).

# Entity Relationship Model

Check ERModel folder for entity relationship model. The relationship between album and musician is 
many to many i.e; one ablum can have one or more musicians and vice versa

# Rest api details

1)   - Description : API to create music album records
     - URL : http://{HOSTIP}:{PORT}/jukeboxrest/v1/album/create
     - Method : POST
     - Request body format : JSON
     - Sample Request body : 
                        {
                            "name" :"",
                            "type":"",
                            "creationDate":"",
                            "price":,
                            "description":"",
                            "musicians":[{
                                "name" : "",
                                "musicType":"Vocalist/Instrumentalist"
                            }]
                          }
  
      - Staus Code : - 200 (For success)
                     - 400 (If there is any constraint violation)
               
2)    -  Description: API to create musician records
      -  URL : http://{HOSTIP}:{PORT}/jukeboxrest/v1/musician/create
      -  Method : POST
      -  Request body format : JSON
      -  Sample Request body : 
                                   {   
                "musicType":"Vocalist/Insturmentalist",
                "name" : "",
                  "albums" : [{
                    "name" : "",
                    "type" : "",
                    "price" : ,
                    "description":"",
                    "creationDate":""
                  }]
            }
      -  Staus Code : - 200 (For success)
                      - 400 (If there is any constraint violation)
               
 3)   -  Description: API to retrieve the list of Music albums by a musician sorted by Date of creation in
         ascending order (i.e Oldest first)
      -  URL : http://{HOSTIP}:{PORT}/jukeboxrest/v1/album/list
      -  Method : GET
      -  Query Params : - musician (required)
                        - orderby=date
   
      -  Response body format : JSON
   
      -  Staus Code : - 200 (For success)
                      - 404 (If there is no musician with that name)
   
  4)  -  Description: API to retrieve the list of Music albums by a musician sorted by price in ascending order
         (i.e Lowest first)
      -  URL : http://{HOSTIP}:{PORT}/jukeboxrest/v1/album/list
      - Method : GET
      - Query Params : - musician (required)
                        - orderby=price
   
      - Response body format : JSON
   
      -  Staus Code : - 200 (For success)
                      - 404 (If there is no musician with that name)
              
   5)    -  Description: API to retrieve the list of musicians for a music album sorted by musician's name
         - URL : http://{HOSTIP}:{PORT}/jukeboxrest/v1/musician/list
         -  Method : GET
         -  Query Params : albumname (required)
         - Response body format : JSON
   
         - Staus Code : -  200 (For success)
                        -  404 (If there is no album with that name)
               
               
   # Note
   Below are the constraints for album
      -Album name (Mandatory, Should be minimum 5 characters)
      - Date of creation (Mandatory)
      - Type of music
      - Price (Mandatory, value between 100 to 1000)
      - Description
      
   Below are the constraints for musician
      - Name (Mandatory, Should be minimum 3 characters)
      - Musician type (either Vocalist or Instrumentalist)
     
  # Dockerization :
      
      For docker version of the above project please check Dockerization folder
      
      1) In order to run as docker image place the target jar and Dockerfile in same folder and run the below command
        
          docker build -t imagename:tagname .  (This will create an image with the jar from alpine jdk 8) 
          
          Then run the image using command docker run -d imageid (image created from above command)
          
          The only problem with this is, you need to change application.properties to with respective db details
       
      2) There is other way, which will create db and java image from docker compose file. Check dockerization folder for 
          docker-compose.yml file
          
          Run below command to install spring boot application and mysql db
          
          docker-compose up (Run this command in the folder where docker-compose.yml is present)
         
          if there is any failure, just stop and run the above command again, this happens as mysql db creation takes time.
          
          To check the logs use command docker containerid -f logs
          
          Once you see logs are clean you can access above rest api with host as localhost and port as 9090 (To change port change docker-compose.yml)
          
         
         Note: Make sure you have docker and docker-compose is installed on your system and docker deamon is up and running
         
          
