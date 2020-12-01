/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


let dlcommentbutton = document.getElementsByClassName("delete-button");
let dlcmtform = document.getElementsByClassName("delete-form");

for (let i = 0; i < dlcommentbutton.length; i++) {
    dlcommentbutton[i].addEventListener("click", function (event) {

        event.preventDefault();
        Swal.fire({
            title: 'Are you sure to delete this product from your cart?',
            text: "You won't be able to revert this!",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Yes, delete it!'
        }).then((result) => {
            if (result.isConfirmed) {
                dlcmtform[i].submit();
            }
        });
    });
}


var isInsufficient = document.getElementById("insufficient");
if (isInsufficient !== null)
{
    Swal.fire(
            {
                icon: 'error',
                title: 'Oops...',
                text: isInsufficient.textContent
            });
}