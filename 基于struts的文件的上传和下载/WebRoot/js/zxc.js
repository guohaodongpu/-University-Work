function checkCurrentPage(input,totalPages){
    if(input.match(/^\D*$/)){
       alert("ֻ����������!");
       document.getElementById("jumpPageBox").value=document.getElementById("pages").value;
       document.getElementById("jumpPageBox").focus();
    }else if(input>totalPages||input.value<=0){
       alert("�Բ����������ҳ������!");
       document.getElementById("jumpPageBox").value=document.getElementById("pages").value;
       document.getElementById("jumpPageBox").focus();
    }
}