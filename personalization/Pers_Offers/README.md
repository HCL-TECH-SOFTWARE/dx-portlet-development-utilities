# Personalization Example (Pers_Offers)

This repository contains the Pers_Offers personalization example code that can be reviewed/compiled/packaged and installed with Microsoft Visual Studio Code (MVC). The MVC-project already contains required scripts (dxclient scripts) to install the Pers_Offers JSP Portlet on HCL Digital Experience (see scripts folder and tasks.json file).

This maven code is based on the detailed instructions mentioned on the HCL Digital Experience Help-Center page [Develop a Personalization Portlet](https://opensource.hcltechsw.com/digital-experience/latest/manage_content/pzn/pzn_portlet) and the intention of this project is to show up the usage of the HCL Digital Experience personalization API.

**Prerequisites:**

This maven project is created on a Microsoft Windows environment on which the pzndemo_db default location is set to:

 **PZN-Database default location:** C:\HCL\wp_profile\PortalServer\derby\pzndemo_db

Code-Changes in the hrf-files are needed when the pzndemo_db will be installed on a different location!

In addition to that two ConfigEngine tasks need to be executed on the HCL Digital Experience server to run this sample correctly.

**UNIX™Linux™:**  
```./ConfigEngine.sh create-pzndemo-users -DPortalAdminPwd=<password> -DWasPassword=<password>```  
```./ConfigEngine.sh install-pzndemo -DPortalAdminPwd=<password> -DWasPassword=<password>```  

**Microsoft Windows™:**  
```ConfigEngine.bat create-pzndemo-users -DPortalAdminPwd=<password> -DWasPassword=<password>```  
```ConfigEngine.bat install-pzndemo -DPortalAdminPwd=<password> -DWasPassword=<password>```  

This two tasks will generate the demo-users and the pzndemo_db database that will be used in this sample Portlet. For details, please check:

[Develop a Personalization Portlet](https://opensource.hcltechsw.com/digital-experience/latest/manage_content/pzn/pzn_portlet)  
[Install the Personalization sample](https://opensource.hcltechsw.com/digital-experience/CF221/manage_content/pzn/pzn_portlet/pzn_demoinstall/)  
