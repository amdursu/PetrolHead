function validatePassword(){
        var pass = document.getElementById("pass").value;
        var rpass = document.getElementById("rpass").value;
        var span = document.getElementById("passValidate");

        if(pass == rpass){
          span.style.color = 'green';
          span.innerHTML = "Password OK";
          return true;
        }
        else{
          span.style.color = 'red';
          span.innerHTML = "Passwords do not match!";
          return false;
        }
        if(pass == '' || rpass == ''){
          span.innerHTML = '';
        }
        return false;
}
