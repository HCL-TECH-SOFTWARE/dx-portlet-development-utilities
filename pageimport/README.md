# pageimport

This component builds a portlet with maven to create a set of pages under a given unique name of a page and adds the WCM portlet to it.

# Building the application

Follow the commands in the below order to generate the Portlet war file.

- Run ```mvn package``` to generate the pageimport DX Portlet war file.
This will generate war file for you to install. You can find the war file under the target directory.

# Deploying the application

Once the war file is generated successfully, follow the below scenario to test it.

- Login to the portal
- Navigate to Administration -> Applications -> Web Modules
- Choose the generated portlet war file to install.

Apply the generated portlet to either a new page or an existing page.

# Related Documents

For Documentations about how the libraries used in the sample code work, you may refer to the following links: Controller SPI (https://opensource.hcltechsw.com/digital-experience/CF207/extend_dx/apis/controller_spi/) and Model SPI (https://opensource.hcltechsw.com/digital-experience/CF207/extend_dx/apis/model_spi/).