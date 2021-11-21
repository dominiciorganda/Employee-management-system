import { HttpClient, HttpEventType } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
@Component({
  selector: 'app-image',
  templateUrl: './image.component.html'

})
export class ImageComponent implements OnInit {
  
  selectedFile: File;
  retrievedImage: any;
  base64Data: any;
  retrieveResonse: any;
  message: string;
  imageName: any;
  constructor(private httpClient: HttpClient,   private router: Router) { }
  ngOnInit(): void {}


  //Gets called when the user selects an image
  public onFileChanged(event) {
    //Select File
    this.selectedFile = event.target.files[0];
  }
  //Gets called when the user clicks on submit to upload the image
  onUpload() {
    console.log(this.selectedFile);
    
    //FormData API provides methods and properties to allow us easily prepare form data to be sent with POST HTTP requests.
    const uploadImageData = new FormData();
    uploadImageData.append('imageFile', this.selectedFile, this.selectedFile.name);
  
    //Make a call to the Spring Boot Application to save the image
    this.httpClient.post('http://localhost:8081/employee/upload', uploadImageData, { observe: 'response' })
      .subscribe((response) => {
        if (response.status == 200) {
          this.message = 'Image uploaded successfully';
        } else {
          this.message = 'Image not uploaded successfully';
        }
      }
      );
  }
    //Gets called when the user clicks on retieve image button to get the image from back end
    getImage() {
    //Make a call to Spring Boot to get the Image Bytes.
    console.log(this.imageName);
    
    this.httpClient.post('http://localhost:8081/employee/get/' + this.imageName,{observe: 'response'})
      .subscribe(
        res => {
          console.log(res);
          this.retrieveResonse = res;
          this.base64Data = this.retrieveResonse.profilePic;
          this.retrievedImage = 'data:image/jpeg;base64,' + this.base64Data;
        }
      );
  }
}