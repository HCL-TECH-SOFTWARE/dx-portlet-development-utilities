# DX Portlet Archetype

This is a Custom Archetype which is based on [Maven Portlet Archetype](https://maven.apache.org/archetypes/maven-archetype-portlet/) and is used for creating DX Portlet.

# Build DX archetype and generate the Portlet war file
Follow the commands in the below order to generate the Portlet war file.

- Run ```mvn clean install``` to generate the archetype jar.

- Below command be referenced for generating DX archetype.
    ```
    mvn archetype:generate -DgroupId=com.hcl -DartifactId=dxportlet -Dversion=1.0-SNAPSHOT -DarchetypeGroupId=com.hcl -DarchetypeArtifactId=dxarchetype -DarchetypeVersion=1.0-SNAPSHOT
    ```

- Once the archetype is build successfully run, ```mvn clean install``` to generate the DX Portlet war file.

# Update DX with new generated portlet

Once the war file is generated successfully, follow the below scenario to test it.

- Login to the portal
- Navigate to Administration -> Applications -> Web Modules
- Choose the generated portlet war file to install.

Apply the generated portlet to either a new page or an existing page.
