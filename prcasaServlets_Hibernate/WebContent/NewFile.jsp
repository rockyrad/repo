<html>
<head>

<script type="text/javascript">
function SetShipping (objDropDown)
{
    var objHidden = document.getElementById("shipping");
    objHidden.value = objDropDown.value; 
}


function setShippingtype (objDropDown)
{
    var objHidden = document.getElementById("shippingtype");
    objHidden.value = objDropDown.value; 
}


function setShippinglocation (objDropDown)
{
    var objHidden = document.getElementById("shippinglocation");
    objHidden.value = objDropDown.value; 
}

function showEntry(obj,optionValue)
{
 //hide all entry selections onchange
 document.getElementById("pickup").style.display="none";
 if(obj.value=="pickup")
 {
  document.getElementById(optionValue).style.display="inline"; 
 }  
}



</script>
</head>
<body>
<form id="myform">

    <br />
    shippingselectbox
    <br />
    <select onchange="showEntry(this,this.value);setShippingtype(this);" name="shippingselectbox">
      <option value="">Shipping Options</option>
      <option value="pickup">Pickup - no charge</option>
      <option value="UPS">UPS Standard Shipping - 3.00</option>
      <option value="Overnight">Overnight Delivery - 14.00</option>

  </select>
    <br />
    <br />
    pickup 
  <span id="pickup" style="display:none;">
<select name="pickup" onchange="setShippinglocation(this)">
      <option>Please Choose a Location</option>
      <option value="Billings">Billings, MT</option>
      <option value="Livingston">Livingston, MT</option>
      <option value="Miles City">Miles City, MT</option>
      <option value="Cody">Cody, WY</option>
      <option value="Sheridan">Sheridan, WY</option>
  </select>
  </span>


    <br />
    <br />
<br />
 shipping (set hidden form tag - shipping amount for paypal)<br />
    <input name="shipping" type="text" value=""/>

    <br />
    <br />
<br />
 shippingtype (set hidden form tag - variable for paypal to send to Chris)<br />
    <input name="shippingtype" type="text" value=""/>
  <br />
  <br />

  <br />

 shippinglocation  (set hidden form tag -  variable for paypal to send to Chris)<br />
  <input name="shippinglocation" id="shippinglocation" type="text" value=""/>

</form>
</body>
</html>