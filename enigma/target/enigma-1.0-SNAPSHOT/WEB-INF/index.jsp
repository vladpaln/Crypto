<%-- 
    Document   : index
    Created on : May 12, 2017, 10:34:24 PM
    Author     : vladpaln
--%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
			padding: 2px;
		}
		
		span {
			display: inline-block;
			padding-left: 10px;
			font-size: 12px;
		}
	</style>
</head>

<body>
	<div style="float: left; display: table;">
	<h1 style="cursor: pointer;">Enigma 4K</h1>
	<form action="/enigma/index" method="POST" autocomplete="off">
		<div>	<label>Rotor #</label>
				<input class="right" type="text" name="rotorCount" value="<c:out value='${rotorCount}' />" size="3" onfocus="this.select();" /><span style="width: 80px;">(97 - 4K)</span>
				<label class="right" style="width: 180px;">Msg ID:</label><span><c:out value='${msgID}' /></span>
		</div>
		<div>	<label>Plugboard #</label>
				<input class="right" type="text" name="pbCount" value="<c:out value='${pbCount}' />" size="3" onfocus="this.select();" /><span style="width: 80px;">(97 - <c:out value='${pbCountMax}' />)</span>
				<label class="right" style="width: 180px;">Directory Seed:</label>
				<input class="right" type="text" name="dirSeed" value="<c:out value='${dirSeed}' />" size="20" onfocus="this.select();" />
		</div>
		<br />
		<div>	<label class="<c:out value='${handleErr}' />">Recipient</label>
				<input type="text" name="handle" value="<c:out value='${handle}' />" placeholder="Recipient Name, Email or Handle (case sensitive)" size="50" onfocus="this.select();" />
		</div>
		<div>	<label class="<c:out value='${passPhraseErr}' />">Crypt Phrase</label>
				<input type="text" name="passPhrase" size="80" value="<c:out value='${passPhrase}' />" placeholder="Crypt Phrase or Password (case sensitive)" onfocus="this.select();" />
		</div>
		<br />
		<div><label style="width: 500px;" class="<c:out value='${textErr}' />">Text <span style="font-size: 8pt;">(allowed chars: A-Za-z, 0-9, standard punctuation)</span></label></div>
		<div><textarea name="text" id="text" rows="20" cols="100" placeholder="Plain Text or Encrypted Text [Allowed Chars: A-Za-z, 0-9, Standard Punctuation]" onfocus="this.select();"><c:out value='${text}' /></textarea></div>
		<div>	<button name="encrypt" type="submit" value="encrypt">Encrypt</button>&nbsp;&nbsp;
				<button name="decrypt" type="submit" value="decrypt">Decrypt</button>&nbsp;&nbsp;
				<button onclick="document.getElementById('text').value = '';"><b>Clear</b></button>
		</div>
	</form>
	</div>
		
	<%--
	<c:forEach var="i" begin="1" end="5">
		<span>Item <c:out value="${i}" /></span>
	</c:forEach>
	--%>
	
	<div class="info" style="text-align: left; display: table; padding: 25px;">
		<b>Parameters</b><br />
		Rotor #: Number of rotors.<sup>1</sup><br />
		Plugboard #: Number of plugboards.<sup>1</sup><br />
		Directory Seed: Used to randomize directory. Default is blank.<sup>1</sup><br />
		Recipient: Recipient email or online handle. Used to encrypt message.<sup>1</sup><br />
		Crypt Phrase: A pass phrase used to encrypt/decrypt text.<sup>1</sup><br />
		Text - Plain text to be encrypted or crypt text to be decrypted.<sup> </sup><br /><br />
		<sup>1</sup> Both parties must have this info.<br /><br />

		<b>About Enigma 4K</b><br />
		Enigma 4K is based on the original with several improvements. The number<br />
		of rotors and plugboards can now be set to any value between 97 and 4K.<br />
		The only limitation is system memory that holds all of the rotors and<br />
		plugboard data. The reflector has also been removed and rotors now have<br />
		46,655 values.<br /><br />
		
		Features<br />
		- each message will have a different seed<br />
		- no reflector, value can map to itself<br />
		- random step size for each rotor<br />
		- random direction rotor rotation<br /><br />
		
		<b>How Enigma 4K Works</b><br />
		Enigma 4K encryption starts by first encoding plain text into its numeric<br />
		equivalent, but instead of encoding letters, Enigma 4K encodes entire<br />
		words. Next a seed is generated using the pass phrase and email/handle.<br />
		Use of email/handle is highly recommended to avoid cracking using common<br />
		pass phrases. The generated seed is used to spawn random rotors, plugboards,<br />
		rotor step direction, and rotor step size. The encoded value is passed<br />
		through a plugboards => rotors sequence 97 times before passing it through<br />
		a plugboard for the last time before converting to base36 text.<br /><br />
	</div>
</body>
</html>
