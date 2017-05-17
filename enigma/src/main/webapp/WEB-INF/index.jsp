<%-- 
    Document   : index
    Created on : May 12, 2017, 10:34:24 PM
    Author     : vladpaln
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
	<link rel="icon" type="image/x-icon" sizes="16x16" href="favicon.ico">
	<title>Enigma 4K</title>

	<style>

		body {
			font-family: "verdana";
			margin-left: 20px;
		}
		
		h1 {
			font-size: 18pt;
		}
		
		label {
			display: inline-block;
			width: 120px;
			padding-top: 5px;
		}
		
		.right {
			text-align: right;
		}
		
		.info {
			font-size: 10pt;
		}

		p {
			display: inline-block;
		}
		
		.err {
			color: red;
		}
		
		input {
			padding: 3px;
		}
		
		span {
			padding-left: 10px;
			font-size: 12px;
		}
	</style>
</head>

<body>
	<div style="float: left; display: table;">
	<h1>Enigma 4K</h1>
	<form action="/enigma/index" method="POST" autocomplete="off">
		<div>	<label>Rotor #</label>
				<input class="right" type="text" name="rotorCount" value="${rotorCount}" size="3" /><span>(97 - 4K)</span>
				<label style="padding-left: 100px;">Msg ID:</label><span>${msgID}</span>
		</div>
		<div>	<label>Plugboard #</label>
				<input class="right" type="text" name="pbCount" value="${pbCount}" size="3" /><span>(97 - 4K)</span></div>
		<br />
		<div>	<label class="${passPhraseErr}">Crypt Phrase</label>
				<input type="text" name="passPhrase" size="80" value="${passPhrase}" placeholder="Crypt Phrase or Password (case sensitive)"/>
		</div>
		<div>	<label class="${handleErr}">Email/Handle</label>
				<input type="text" name="handle" value="${handle}" placeholder="Recipient Email or Handle (case sensitive)" size="40" />
		</div>
		<br />
		<div><label style="width: 500px;" class="${textErr}">Text <span style="font-size: 8pt;">(allowed chars: A-Za-z, 0-9, standard punctuation)</span></label></div>
		<div><textarea name="text" rows="15" cols="100" placeholder="Plain Text or Encrypted Text [Allowed Chars: A-Za-z, 0-9, Standard Punctuation]">${text}</textarea></div>
		<div><button name="encrypt" type="submit" value="encrypt">Encrypt</button>&nbsp;&nbsp;<button name="decrypt" type="submit" value="decrypt">Decrypt</button></div>
	</form>
	</div>
	
	<div class="info" style="text-align: left; display: table; padding: 50px;">
		<b>Parameters</b><br />
		Rotor # - Number of rotors.<br />
		Plugboard # - Number of plugboards.<br />
		Crypt Phrase - A pass phrase used to encrypt/decrypt text.<br />
		Email/Handle - Recipient email or online handle. Used to encrypt message.<br />
		Text - Plain text to be encrypted or crypt text to be decrypted.<br />
		note: Both parties must know rotor #, plugboard #, crypt phrase and<br />
		email/handle. The email/handle value can be anything.<br /><br />


		<b>About Enigma 4K</b><br />
		Enigma 4K is based on the original with several improvements. The number<br />
		of rotors and plugboards can now be set to any value between 97 and 4K.<br />
		The only limitation is system memory that holds all of the rotors and<br />
		plugboard data. The reflector has also been removed and rotors now have<br />
		46,655 values.<br /><br />
		
		Features<br />
		- each message with have a different seed<br />
		- no reflector, value can map to itself<br />
		- random step size for each rotor<br />
		- random direction rotor rotation<br /><br />
		
		<b>How Enigma 4K Works</b><br />
		Enigma 4K encryption starts by first encoding plain text into its numeric<br />
		equivalent, but instead of encoding letters, Enigma 4K encodes entire<br />
		words. Next a seed is generated using the pass phrase and email/handle.<br />
		Use of email/handle	is highly recommended to avoid cracking using common<br />
		pass phrases. The generated seed is used to spawn random rotors, plugboards,<br />
		rotor step direction, and rotor step size. The encoded value is passed<br />
		through a plugboards => rotors sequence 97 times before passing it through<br />
		a plugboard	for the last time before converting to base36 text.<br /><br />

		cryptText = plugboard( f<sub>97</sub>(rotor(plugboard(plainText))) )<br /><br />
		
		<%-- Character.toString((char)(0x3B10)) --%>
	</div>
</body>
</html>
