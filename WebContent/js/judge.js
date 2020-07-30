function addErrorText(elem, text){
    $(elem).next().find('p').text(text);
    $(elem).next().find('p').addClass('show');
    $(elem).addClass('error-input');
}

function judge(){
    var usernameRegex = /^\w{2,15}$/;
    var passwordRegex = /^[a-zA-Z0-9]{5,20}$/;
    var phoneRegex = /^1[0-9]{10}$/;
    var usernameElem = $('#username')[0];
    var passwordElem = $('#password')[0];
    var passwordAgainElem = $('#password-again')[0];
    var phoneElem = $('#phone')[0];
    var flag = {};

    if(usernameElem.value === ""){
        addErrorText(usernameElem, '用户名不能为空');
        flag['username'] = false;
    }

    if(passwordElem.value === ""){
        addErrorText(passwordElem, '密码不能为空');
        flag['password'] = false;
    }

    if(passwordAgainElem.value === ""){
        addErrorText(passwordAgainElem, '密码不能为空');
        flag['password-again'] = false;
    }

    if(phoneElem.value === ""){
        addErrorText(phoneElem, '手机号不能为空');
        flag['phone'] = false;
    }

    if(!$.isEmptyObject(flag)){     // 空判
        return false;
    }

    flag = {};

    if(!usernameRegex.test(usernameElem.value)){
        addErrorText(usernameElem, '用户名不符合要求');
        flag['username'] = false;
    }

    if(!passwordRegex.test(passwordElem.value)){
        addErrorText(passwordElem, '密码不符合要求');
        flag['password'] = false;
    }

    if(!(passwordElem.value === passwordAgainElem.value)){
        addErrorText(passwordAgainElem, '密码不一致');
        flag['password-again'] = false;
    }

    if(!phoneRegex.test(phoneElem.value)){
        addErrorText(phoneElem, '手机号不符合要求');
        flag['phone'] = false;
    }

    if(!$.isEmptyObject(flag)){     // 空判
        return false;
    }
    
    return true;
}