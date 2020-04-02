# Simple Java Bot

### All API you can find att :
    - http://localhost:8080/v2/api-docs
    - http://localhost:8080/swagger-ui.html 
    
### And check int in DB 
    - http://localhost:8080/h2-console/
    
## Start it like simple Spring Boot Application
    ```@SpringBootApplication
       public class JavaBotApplication {
       
           private static final Logger LOG = LoggerFactory.getLogger(JavaBotApplication.class);
       
           public static void main(String[] args) {...
           ```
           
## Usage 
    - spring-boot-starter
    - spring-boot-starter-data-jpa
    - h2database
    - telegrambots-spring-boot-starter
    - swagger2
    - mockito, junit
    
### TESTDB
    >    INSERT INTO capitals_Info ( id, name, description )
         VALUES( 1, 'Москва', 'Москва – столица России, многонациональный город на Москве-реке в западной части страны. В его историческом центре находится средневековая крепость Кремль – резиденция российского президента. ');
         INSERT INTO capitals_Info ( id, name, description )
         VALUES( 2, 'Санкт-Петербург', 'Санкт-Петербург – русский портовый город на побережье Балтийского моря, который в течение двух веков служил столицей Российской империи. Он был основан в 1703 году Петром I.');
         INSERT INTO capitals_Info ( id, name, description )
         VALUES( 3, 'Брест', 'Брест — город на юго-западе Белоруссии, административный центр Брестской области и Брестского района. Территория города — 146,12 км². Hаселение города составило 350 616 человек. ');
         INSERT INTO capitals_Info ( id, name, description )
         VALUES( 4, 'Киев', 'Киев – столица Украины, расположенная на реке Днепр. Город известен памятниками религиозной архитектуры и историческими музеями');