import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit{

  public users = {
    username :'' ,
    password :'' ,
    firstname :'' ,
    lastname :'' ,
    email :'' ,
    phoneNumber :'' ,  
  };
  
  constructor(private router: Router , private userService : UserService , private snack:MatSnackBar){};
  
  ngOnInit(): void {
   
  }

  formSubmit(){
    console.log(this.users);
    if(this.users.username == '' || this.users.username ==null)
    {
      // alert("Username is required..");
      //MatSnackBar used here 
      this.snack.open("Username is required !!" , "" ,{
      duration: 3000
      })
      return ;
    }

    //  this.userService.addUsers(this.users).subscribe(
    //   data => {
    //       console.log("this is the data " + data);
    //       alert ("success");
    //   }
    //  );
    
    this.userService.addUsers(this.users).subscribe(
      (data) => {
        console.log(data);
        alert("success login");
      },
      (error)=>{
        console.log(error);
        // alert("somethiing went wrong");
         //MatSnackBar used here 
        this.snack.open("Something went wrong!!" , " " ,{
          duration: 3000
        })
      }
    )
    
  }

}
