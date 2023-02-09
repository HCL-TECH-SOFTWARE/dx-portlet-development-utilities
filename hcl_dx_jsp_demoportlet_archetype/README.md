# DX Portlet Archetype
This is a Custom Archetype which is based on [Maven Portlet Archetype](https://maven.apache.org/archetypes/maven-archetype-portlet/) and is used for creating a HCL DX JSP Demo Portlet on a Microsoft Visual Studio IDE. 
With the Custom Archetype it is possible to generate a ready for use HCL DX JSP Demo Portlet.

The sample can be used to show up a whole development lifecycle of an HCL DX JSP Demo Portlet.
1) Portlet Generation 
2) WAR-File Generation (Deployment artifact)
3) Portlet Deployment
4) Optional Test it (by adding the demo portlet manually to a sample page)
5) Portlet Undeployment 

# Prerequistes to build and deploy the sample 
- Docker or Podman need to be installed locally
- An HCL Digital Experience Server container image need to be installed and need to be running local on the docker/podman instance 
  (For details, please check: https://opensource.hcltechsw.com/digital-experience/cf205/platform/docker/docker_image_deployment/)
- HCL DXClient needs to be installed on the local system 
  (For details please check: https://help.hcltechsw.com/digital-experience/9.5/containerization/dxclient.html)

# Build and install the DX archetype
Follow the commands in the below order to generate the Portlet war file.

1) Run ```mvn clean install``` to generate the archetype jar.

2) Below command be referenced for generating DX archetype.
    ```
    mvn org.apache.maven.plugins:maven-archetype-plugin:3.1.2:generate -DarchetypeArtifactId="hcl_dx_jsp_demoportlet_archetype" -DarchetypeGroupId="com.hcl.dx.demo" -DarchetypeVersion="1.0" -DgroupId="com.hcl.dx" -DartifactId="jspdemoportlet"
    ```
# Build the WAR-File (Deployment artifact)
1) Go into the subdirectory that matches the ArtifactoryID that was used. 
2) Run  ```mvn clean package``` to generate the HCL DX JSP Demo Portlet war file. 
   You can find the war file under a path similar to this: ```/my_new_war_root/{ArtifactoryID}/target/jspdemoportlet-1.0.war```.

# Deploy/Update or Undeploy the Portlet using DXClient
The Custom Archetype already include sample XML files to deploy/update and undeploy the HCL DX JSP Demo Portlet on a local HCL-DX container image. 
When using Microsoft Visual Studio Code IDE you will also find two tasks that can be used for deployment/update(task: deploy_OR_Update_Portlet) and undeployment(task: undeploy_Portlet). 

If a manual deployment or update of the portlet is needed the following dxclient command can be used:
dxclient deploy-portlet -hostname <hostname> -dxPort <port> -dxConnectUsername <user> -dxConnectPassword <password> -dxUsername <user> -dxPassword <password> -warFile ${workspaceFolder}/target/jspdemoportlet-1.0.war  -xmlFile <workspaceFolder>/scripts/DeployPortlet.xml

If a manual undeployment of the portlet is needed the following command can be used:
dxclient undeploy-portlet -hostname <hostname> -dxPort <port> -dxConnectUsername <user> -dxConnectPassword <password> -dxUsername <user> -dxPassword <password> -xmlFile ${workspaceFolder}/scripts/UndeployPortlet.xml

# Test the generated Portlet
As soon as the HCL DX JSP Demo Portlet is deployed the following steps are needed to test the portlet:

1) create a new empty page on your HCL DX Portal (Make sure that the permissions are set correctly)
2) add the Portlet to the page and make sure that the portlet permissions are set correctly
3) Open the page and test the portlet

