# Project

## Set up
1. open this project  [link](https://bitbucket.org/6210400167/6210400167/src/master/)
2. Click to clone button
3. Click to copy icon or copy clone link
4. Go to your directory where you want to set up this project
5. Right click and select "Git Bash Here" 
6. Paste the copied link from clone by right click and select "paste"
7. Press Enter and wait 
8. When clone process finished open project folder
9. Set up font

    for window 
    
        go to \src\main\resources\font\
        and double click install all files. 
    for macOS
    
        open /src/main/resources/font/
        and right click and choose install.
        
   
10. Open launch folder
11. Open file 6210400167.jar
```
#Example

user@LAPTOP-UA4REVOK MINGW64 /d/Java_lab
$ git clone https://Peerawich_Tantavachkij@bitbucket.org/6210400167/6210400167.git

```

### \*If you can't find a clone Link
1. Go to this project [link](https://bitbucket.org/6210400167/6210400167/src/master/)
2. Click Button  "..." and select "Download repository"
3. Open zip file
4. Extract file

### \* If you double clicked and program isn't launch.
*****Use git bash*****
1. Right click and select "Git Bash Here"
2. Type command
```
#Example
$ java -jar 6210400167.jar
```

***** Use command prompt *****
1. Open cmd and change directory to launch folder in the file
2. Type command
```
#Example
cd D:\Java_lab\project\6210400167\launch
java -jar 6210400167.jar
```
## \* If you right clicked and can't find "Git Bash Here"
1. Open cmd and change directory that you want to set up
2. Place clone link
```
#Example
cd D:\Java_lab
git clone https://Peerawich_Tantavachkij@bitbucket.org/6210400167/6210400167.git
```


## Structure
* .idea → เก็บไฟล์การตั้งค่า project ของ intellij
* data → เก็บข้อมูลสำหรับใช้ใน project ในรูปแบบของ csv file
* launch → เก็บ jar file ที่ build แล้ว
  * data → เก็บข้อมูล(csv files)สำหรับ jar file ใน launch
* src
  * main
      * java
         * condoProject
            * controller → เก็บ controller สำหรับควบคุมการทำงานของหน้าต่างๆในproject
            * model → เก็บ class modelที่นำไปใช้ใน controller
            * service →เก็บ class ที่เป็นการอ่านไฟล์ต่างๆ
      * resource → เก็บไฟล์รูปภาพ และ .fxml
         * font → เก็บfont
* target → เก็บข้อมูลที่ได้จากการ build


## สรุปสิ่งที่พัฒนาในแต่ละสัปดาห์
   * สัปดาห์ที่ 1
           >สร้าง LoginController
           >สร้าง model Staff, Admin, StaffAccount, AdminAccount และ .fxmlต่างๆ
           >add maven to project
   * สัปดาห์ที่ 2
         
           >fix AdminController
           >add Staff list table โดยใช้ TableView
   * สัปดาห์ที่ 3
           
           >สร้าง AccountDataSource แบบ hard code
           >ลบ StaffAccount AdminAccount เปลี่ยนไปเก็บรวมกันในClass AccountList,Account,Staffแทน
            โดยใช้การpolymorph
           >แก้ หน้าlogin และ Override methodสำหรับการตรวจสอบaccount
   * สัปดาห์ที่ 4
   
          >เปลี่ยน AccountDataSource จาก harcode เป็นแบบ อ่านและเขียนไฟล์ csv
          >สร้าง folder dataสำหรับเก็บไฟล์ข้อมูล
          >สร้างfolder service
          
   * สัปดาห์ที่ 5
      
          >แก้ไขหน้าlogin ที่ก่อนหน้านี้adminสามารถเข้าใช้งานหน้า staffได้
          >สร้างRoom's list table
          >สร้าง addANewRoomController
    
   * สัปดาห์ที่ 6
         
         >แก้ไขการ ad guest ให้สามารถ ad CoGuestได้ภายหลัง
         >สร้างไฟล์ README.md
         >สร้างmethod getData สำหรับใช้เขียนไฟล์ของ Account และ Staff
         
   * สัปดาห์ที่ 7
   
          >ใส่รูปภาพให้ จดหมาย พัสดุ และเอกสาร
          >เพิ่มปุ่มปิดการใช้งานเจ้าหน้าที่ส่วนกลางให้ผู้ดูแลระบบ
          
## แก้ไขโปรเจค รอบที่ 2
    > ปรับfont เพื่อให้แสดงผลได้ เพิ่มวิธีการติดตั้งfont ใน README.md
    > ปรับแต่ง icon สำหรับเข้าถึงข้อมูลผู้จัดทำให้ชัดเจนมากขึ้น และปรับขนาดหน้าแสดงข้อมูลผู้จัดทำให้เหมาะสม
    > ปรับแต่ง GUI หน้าเพิ่มจดหมายให้เป็นระเบียบมากขึ้นโดย แบ่งเป็น 2 ส่วนคือ Receiver Info. ที่เป็นข้อมูลของผู้รับ(ผู้ที่พักอาศัยในcondo)และ Item Info.(รายละเอียดของจดหมายประเภทนั้นๆ)
    > แก้คู่มือการใช้ให้สอดคล้องกับสิ่งที่แก้ไปข้างต้น
     







  



