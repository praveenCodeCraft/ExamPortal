import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { LoginService } from 'src/app/services/login.service';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginData :any = {
      username : '',
      password : '' 
     }
  
  constructor(private snack : MatSnackBar , private service : LoginService){};
  
  ngOnInit(): void {
   
  }

  formSubmit(){
    debugger
    console.log("login for submitted");

    if(this.loginData.username.trim() == '' || this.loginData.username == null){
      this.snack.open("username and password is required" , '' , {
        duration:3000,
      });
      return ;
    }

    if (this.loginData.password.trim()==null || this.loginData.password == null) {
      this.snack.open('username and password is required','',{
          duration : 3000,
      });
      return ;
    }
   


    debugger
    //call server here 
    this.service.loginUser(this.loginData).subscribe(
      (data) => {
        console.log("Success");
        console.log(data);
      },
      (error) => {
        console.log("error");
        console.log(error);
      }
    )
  }


}
