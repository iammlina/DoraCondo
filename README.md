Yosita Jinda
6210451411
**รายละเอียดในแต่ละ WEEK & commit**
WEEK 01 (20 - 26 Sep. 2020) : commit 1 - 5
    สร้าง GUI Controller ของ Application ไปบางหน้า และได้เพิ่ม maven ใน project 
    ได้ทำในส่วนของหน้าแรกที่สามารถกดไปยังหน้า About และหน้า Sign in 
    และยังได้สร้าง Class ที่เป็น Model ของ Account Admin (ผู้ดูแลระบบ) และ Staff (เจ้าหน้าที่ส่วนกลาง)
    ซึ่งหน้า Sign in สามารถ Login เข้าระบบในส่วนของ Admin ไปยังหน้าหลักของ Admin และสามารถ Logout ไปยังหน้าหลักของโปรเจคได้
    ในส่วนของ bitbucket ได้เพิ่มไฟล์ (.pdf) ที่เป็น gui ที่ร่างวาดเขียนร่างโปรเจคไว้
WEEK 02 (27 Sep. - 3 Oct. 2020) : commit 6 & 7
    สร้าง GUI Controller ของ Application ในส่วนของหน้าเปลี่ยนรหัส หน้า Pop up และหน้าหลักของ Staff (เจ้าหน้าที่ส่วนกลาง)
    และสร้าง Maincontroller (interface) ต่อมาเปลี่ยนชื่อเป็น CheckAccount สำหรับทำงานในส่วน Account Admin & Staff 
    และในส่วน Method ของ Change Password ยัง Error ไม่สามารถเปลี่ยนรหัสได้
    ซึ่งหน้า Sign in สามารถ Login เข้าระบบในส่วนของ Staff ไปยังหน้าหลักของ Staff ได้
    ทั้ง Admin & Staff สามารถกด Change Password ไปยังหน้า Change Password ที่เปลี่ยนรหัสได้
WEEK 03 (4 - 10 Oct. 2020) : commit 8 & 9
    ได้เขียนและอ่านไฟล์ (.csv) ซึ่งเป็นไฟล์ในส่วนของ Account Admin & Staff ที่ไว้สำหรับ Login
    และสร้าง AccountData (interface) สำหรับอ่านเขียนไฟล์ในการ Sign in เข้าถึงระบบของ Admin & Staff
    และได้ทำในส่วนของการเพิ่มเจ้าหน้าที่ส่วนกลางในหน้าหลักของ Admin (AddStaff) ซึ่งสามารถเพิ่ม Staff ได้
WEEK 04 (11 - 17 Oct. 2020) : commit 10 & 11
    ได้ทำในส่วนของหน้าแสดงเจ้าหน้าที่ส่วนกลางทั้งหมดในหน้าหลักของ Admin (AllStaff) 
    ซึ่งแสดงข้อมูลของ Staff ลงในตาราง (tableview) ข้อมูลที่แสดงมีทั้ง Name, Username, Password, Time(เวลาล่าสุดที่ Staff ได้เข้าระบบ)
    และยังได้สร้าง Class StringConfiguration สำหรับแสดงข้อมูลลงในตาราง 
WEEK 05 (18 - 24 Oct. 2020) : commit 12 - 15
    Update file.jar (6210451411-jar.jar) ซึ่งสามารถอ่านข้อมูลในไฟล์ .csv ได้แล้ว (อยู่ใน directory data)
    Update README.md & GUI ของ Application ที่ได้ทำค้างไปบางส่วน
    ในส่วนของหน้าเพิ่มเจ้าหน้าที่ส่วนกลางของระบบ Admin นั้นสามารถเพิ่มรูป (รูปที่ถูกเพิ่มจะถูก copy มาไว้ใน directory images )
    แล้วมาแสดงรูป (imageview) และบันทึกรูปที่เพิ่มมาได้ (รูปที่ถูกบันทึกมาอยู๋ใน directory image_of_Staff ซึ่ง directory อยู่ใน resources)
    และในส่วนของการแสดงข้อมูลเจ้าหน้าที่ส่วนกลางในตารางได้เพิ่มแสดงรูปภาพของแต่ละ Staff ไว้ด้วย 
    เมื่อคลิ๊กข้อมูลของ Staff ที่อยู๋ในตารางที่แสดงอยู่ฝั่งซ้าย (showSelected)
    ข้อมูลของ Staff นั้นจะแสดงมาฝั่งขวา มีทั้ง Name, Username, Time(เวลาล่าสุดที่ Staff ได้เข้าระบบ) และรูปภาพ (รูปที่มาแสดงนั้นได้ดึงมาจาก directory image_of_Staff ที่ได้ทำการบันทึกรูปไว้)
    ในส่วนของการเปลี่ยนรหัสผ่านนั้น Account ของ Staff นั้นสามารถเปลี่ยนรหัสได้แล้ว (19/10)
    สร้าง directory สำหรับไฟล์ .jar for RUN    
WEEK 05 (25 Oct. - 1 Nov. 2020) : commit 16 - present
    Update README.md & GUI ของ Application ทั้งหมด และได้ทำครบทุกส่วน และได้เพิ่มการค้นหา การลบ การเปลี่ยนแปลงข้อมูลไปเช่นกัน 
    ของหน้าหลักเจ้าหน้าที่ส่วนกลาง (staff) มีดังนี้
    ในส่วนนำเข้าของ (ของที่นำเข้าระบบ จดหมาย เอกสาร พัสดุ) IMPORT : ได้ทำคล้ายกับแสดงข้อมูลเจ้าหน้าที่ส่วนกลางในตารางทั้งรูปภาพและข้อมูล 
    ซึ่งหน้าขวาสุดนั้นคือส่วนของเพิ่มของเข้าระบบ
    ในส่วนนำส่งออกแก่ผู้พัก EXPORT : ก็ได้ทำคล้ายแสดงข้อมูลเจ้าหน้าที่ส่วนกลางในตารางทั้งรูปภาพและข้อมูล ซึ่งบนขวาสำหรับใส่ชื่อผู้พักที่มารับของเมื่อกดส่งออก ข้อมูลในตารางจะมีการเปลี่ยนแปลง
    ในส่วนแสดงข้อมูลสรุปทุกอย่าง ICON บนขวา : ก็ได้ทำคล้ายแสดงข้อมูลเจ้าหน้าที่ส่วนกลางในตารางทแต่ไม่มีรูปภาพ
    และส่วนสุดท้ายคือ ICON ล่างซ้าย ข้อมูลเกี่ยวกับห้องพักและผู้พัก ซึ่งได้ทำคล้ายกับเมื่อที่กล่าวไว้ข้างบน รวมทั้งมีกรเพิ่มห้องพักและผู้พักเข้าห้อง แต่ไม่มีรูปภาพของส่วนนี้
    ได้ทำการตกแต่ง ตรวจสอบ และทำไกด์ .pdf สำหรับการใช้
    
**วิธีการติดตั้งโปรแกรม** 
    ในกรณีที่ไม่สามารถเปิดโปรแกรมได้จากการ double click 6210451411-jar.jar ให้เปิด commant prompt หรือ terminal หรือ bash แล้วใช้คำสั่ง 'java -jar 6210451411.jar'
    
**การวางโครงสร้างไฟล์**
    - directory data : สำหรับเก็บไฟล์ .csv ซึ่งในนี้มีไฟล์ AdminAccount.csv (เก็บข้อมูล username และ password ของผู้ดูแลระบบไว้)
                       ไฟล์ StaffAccount.csv (เก็บข้อมูล name, username, password, สถานะใช้งาน,ไฟล์ path รูปภาพ และวันเวลาที่ Login ล่าสุด ของเจ้าหน้าที่ส่วนกลางไว้)
                       ไฟล์ Letter.csv : สำหรับเก็บข้อมูลจดหมาย (ชื่อผู้รับ, หมายเลขห้องพัก, ข้อมูลผู้ส่ง,ขนาดช่องจดหมาย, ไฟล์ path รูปภาพ, ชื่อผู้ใช้เจ้าหน้าที่ส่วนกลางที่นำและออกข้อมูลของ,สถานะของ, เวลา และชื่อผู้มารับของ)
                       ไฟล์ Document.csv : สำหรับเก็บข้อมูลเอกสาร (ชื่อผู้รับ, หมายเลขห้องพัก, ข้อมูลผู้ส่ง, ขนาดเอกสาร , ความสำคัญ ,ไฟล์ path รูปภาพ, ชื่อผู้ใช้เจ้าหน้าที่ส่วนกลางที่นำและออกข้อมูลของ,สถานะของ, เวลา และชื่อผู้มารับของ)
                       ไฟล์ Box.csv : สำหรับเก็บข้อมูลพัสดุ (ชื่อผู้รับ, หมายเลขห้องพัก, ข้อมูลผู้ส่ง, ชื่อบริษัทส่งของ , หมายเลขพัสดุ, ความกว้าง , ขนาดพัสดุ ,ไฟล์ path รูปภาพ, ชื่อผู้ใช้เจ้าหน้าที่ส่วนกลางที่นำและออกข้อมูลของ,สถานะของ, เวลา และชื่อผู้มารับของ)
                       ไฟล์ Room.csv : สำหรับเก็บข้อมูลห้องพักและผู้พักห้องนั้นๆ (หมายเลขห้อง, ชั้นของห้อง, ประเภทห้อง, ชื่อผู้พัก, อีเมลผู้พัก, เบอร์โทรศัพท์ผู้พัก, จำนวนผู้พักในห้อง, สถานะของห้อง)
                       ซึ่งใน directory นี้สำหรับการทำงานของ Main
    - directory images : สำหรับรูปที่ถูกเพิ่มเข้าจะทำการ copy เก็บไว้ใน directory นี้
    - directory DoraCondo_jarFile_forRun\6210451411 : directory นี้มี directory data และ directory images ไว้เพื่อเก็บไฟล์ตามที่กล่าวไว้ข้างบน
                                                      แต่นี้สำหรับไฟล์ .jar เท่านั้น ข้อมูลใน directory แต่ละอย่างจะไม่เหมือนกับ directory ข้างบน
                                                      และไฟล์คือไฟล์ 6210451411-jar.jar สำหรับรันโปรแกรมโปรเจคนี้
    - directory src\main : สำหรับเก็บไฟล์โค้ดโปรแกรมโปรเจคนี้ ซึ่งในนี้แบ่ง directory เป็น 2 คือ java & resources
        1. directory src\main\java\condo\dora : ในนี้ก็ยังแบ่ง directory เป็น 3 คือ controllers, models และ services
            1.1 directory src\main\java\condo\dora\controllers : สำหรับเก็บไฟล์ .java ที่ทำหน้าเป็น controller ในแต่ละไฟล์ หน้าต่างๆ (.fxml)
            1.2 directory src\main\java\condo\dora\models : สำหรับเก็บไฟล์ .java ที่ทำหน้าเป็น models
            1.3 directory src\main\java\condo\dora\services : สำหรับเก็บไฟล์ .java ที่ทำหน้าเป็น services ต่างๆที่นอกเหนือจาก 2 ข้อข้างบน
        2. directory src\main\resources : สำหรับเก็บไฟล์ .fxml และยังมี directory ต่างๆ มีทั้งหมด 4 คือ icon, image_of_Staff, logo และ pic
            2.1 directory icon : สำหรับเก็บไฟล์รูปที่เป็น icon 
            2.2 directory image_of_Staff : สำหรับเก็บไฟล์รูปที่เป็นรูปประจำตัวของเจ้าหน้าที่ส่วนกลาง
            2.3 directory logo : สำหรับเก็บไฟล์รูปที่เป็น logo ที่มีตัวอักษร
            2.4 directory pic : pic เป็นคำย่อมาจากคำว่า picture สำหรับเก็บไฟล์รูปต่างๆที่นอกเหนือจาก 3 ข้อข้างบน
            2.5 directory letter : สำหรับเก็บไฟล์รูปจดหมายที่ได้ทำการเพิ่มเข้าระบบแล้ว
            2.6 directory document : สำหรับเก็บไฟล์รูปเอกสารที่ได้ทำการเพิ่มเข้าระบบแล้ว
            2.7 directory box : สำหรับเก็บไฟล์รูปพัสดุที่ได้ทำการเพิ่มเข้าระบบแล้ว.
            
**การเข้าระบบเริ่มต้นใช้งาน**    
    ผู้ดูแลระบบ (Admin)
        username : admin
        password : 123456
    เจ้าหน้าที่ส่วนกลาง (Staff) ตอนนี้มีทั้งหมด 4 account
        1. username : dewey
           password : 1175
        2. username : fox
           password : 1111
        3. username : map
           password : 1234
        4. username : backpack
           password : 5555