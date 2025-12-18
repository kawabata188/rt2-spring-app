/**
 * 
 */

 function confirmDelete(){
	 return confirm("本当に削除しますか？");
 }
 
 function togglePassword(id){
	 const input=document.getElementById(id);
	 if(input.type==="password"){
		 input.type="text";
	 }else{
		 input.type="password";
	 }
 }