/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var sliderMin = document.getElementById("rangeMin");

var outputMin = document.getElementById("outputMin");

outputMin.innerHTML = sliderMin.value;

sliderMin.oninput = function ()
{
    outputMin.innerHTML = this.value;
};

var sliderMax = document.getElementById("rangeMax");
var outputMax = document.getElementById("outputMax");

outputMax.innerHTML = sliderMax.value;

sliderMax.oninput = function ()
{
    outputMax.innerHTML = this.value;
};




