# Chinese Herbal Manaygment System
COMP3000 Final Year Project  
Allocated Supervisor: Dr Vivek Singh

# Extended project description

For this project, I will develop an android application that will use a phones front facing camera to track a pen stylus on a desk and recognise the characters being written. The system would have to detect when the stylus is down, when a character has finished being written, and when a new character is being started.

A possible extension of the application could also be to detect when the user wishes to erase the previous text, either by detecting when the stylus has been turned over, or when the user scribbles something out. A more favourable potential would be to implement a form of artificial intelligence such as Classification or Neural Network to identify the characters written.

To do this, the live camera feed from the front facing camera will be used as the input image for the various algorithms. The application will need to be able to extract or locate the nib of the stylus and use motion tracking techniques to calculate the changes in x and y positions of the nib.

By repeating this process over multiple frames, a sequence of coordinates can be obtained, from which a separate algorithm will be used to recognise the character written. This algorithm could be a machine learning algorithm.

The application would need to be able to work in real time. Assuming the standard front-facing camera has a frame rate of 30fps, the application would have less than 30ms to perform all the object recognition calculations.

To test and provide feedback to the user, the application needs to display back to the user the live camera feed but showing the calculated position of the nib. It would also need to display to the user a sort of “dot-to-dot” of the estimated position of the nib as a 2D projection, to allow the user to see what they would have written.

13.1.附录一：用户指南
 
部署开发环境（windos11）
本项目是由java语言开发的web应用程序，其中前端是由vue框架开发而成，后端是springboot框架开发而成，在开发时需要执行以下步骤:
1.安装java的jdk运行环境
2.安装Mysql服务
3.安装Redis服务
4.安装git客户端
5.安装IntelliJ IDEA 程序编译工具
6.配置maven环境
7.安装node.js环境
8.启动前端web框架
9.启动后端程序
10. 访问前端页面
11. 注册用户并访问系统
 以上是部署开发环境的步骤，对于部署项目到服务端还需要安装nginx代理，并且要把前端项目进行打包部署到nginx映射目录。
远程版本
本项目运行在本地服务器上，用户可以访问项目地址http://127.0.0.1:5173/和http://127.0.0.1:5173/home 浏览系统后台服务和用户端，本项目具有以下用户帐户：
 角色	用户名	密码
管理员	admin	123456
用户	user	123456
