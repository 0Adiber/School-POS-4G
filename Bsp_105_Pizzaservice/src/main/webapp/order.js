function amount(name, amount) {
    if((document.getElementById(name+"_amount").valueAsNumber === 10 && amount === 1) || (document.getElementById(name+"_amount").valueAsNumber === 0 && amount === -1))
        return;
    document.getElementById(name+"_amount").value = document.getElementById(name+"_amount").valueAsNumber + amount;
}

function validate() {
    let address = document.getElementById('address').value;
    
    if(!address)
        return false;
    
    let amounts = document.querySelectorAll('[id*="_amount"]');
    
    let valid = false;
    for (let i of amounts) {
        if(i.value > 0)
            valid = true;
    }
        
    return valid;
}
