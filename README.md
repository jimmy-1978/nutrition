# nutrition

eclipse version used :
- Eclipse IDE for Enterprise Java and Web Developers (includes Incubating components)
- Version: 2022-03 (4.23.0)
- Build id: 20220310-1457

1) New / Project :
- New Dynamic Web Project
- Tomcat Server : Apache Tomcat v10.0 + JRE = Workbench default JRE
- Dynamic web module version = 5.0 (by default - linked to tomcat v10.0 version)
- add source folder "src/test/java"
- Generate web.xml deployment descriptor = true

2) Configure / Convert to Maven Project
- packaging = war

3) Maven
- Preferences / Maven / Installations : apache-maven-3.8.5
- Preferences / Maven / User Settings : user settings (E:\env\maven\apache-maven-3.8.5\conf\settings.xml)
