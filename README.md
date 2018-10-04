# CROSS-SITE REQUEST FORGERY - Anti-CSRF token 
 Prevent CSRF vulnerabilities by generating Cross-Site Request Forgery Tokens in server side and validating them before respond to any client request.

There are two approaches by which Cross-site Request Forgery (CSRF)  

# 1. Synchronizer Token Pattern
synchronizing the Cookie with an anti-CSRF token that has already been provided to the browser.
Any state changing operation requires a secure random token (e.g., CSRF token) to prevent CSRF attacks.The CSRF token is added as a hidden field for forms or within the URL if the state changing operation occurs via a GET
The server rejects the requested action if the CSRF token fails validation.

# 2. Double Submit Cookie
Storing the CSRF token in session is problematic, an alternative defense is use of a double submit cookie. A double submit cookie is defined as sending a random value in both a cookie and as a request parameter, with the server verifying if the cookie value and request value match.e. 
When a user authenticates to a site, the site should generate a (cryptographically strong) pseudorandom value and set it as a cookie on the user's machine separate from the session id. The site does not have to save this value in any way, thus avoiding server side state. The site then requires that every transaction request include this random value as a hidden form value (or other request parameter).
 

 # For more details 
 http://littledidiknowbyishani.blogspot.com/2018/08/cross-site-request-forgery-csrf.html
