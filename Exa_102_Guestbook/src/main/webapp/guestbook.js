function validate() {
    let nickname = document.getElementById('nickname').value;
    let email = document.getElementById('email').value;
    
    if(!nickname || !email)
        return false;
    
    return true;
}
