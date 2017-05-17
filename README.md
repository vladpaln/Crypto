# Enigma4K
An advanced version of the enigma machine.  
  
Features
- word encoding instead of letter encoding
- up to 4,000 rotors
- each rotor will have 46,655 values
- up to 4,000 plugboards
- each plugboard will have 46,655 values
- no reflector, value can map to itself
- random step size for each rotor
- random direction rotor rotation
- random seed generation, Enigma4K will never encrypt a message using the same settings

Usage  
- run code  
- go to http://localhost:8084/enigma  

Encrypt Usage
- enter pass phrase, this can be anything (both parties must have this information)  
- enter recipient email/handle, this can be anything recipient specific (both parties must have this information)  
- enter text
- hit encrypt buttom

Decrypt Usage
- enter pass phrase (same as used to encrypt message)
- enter reipient email/handle (same as used to encrypt message)
- enter encrypted text
- hit decrypt buttom


