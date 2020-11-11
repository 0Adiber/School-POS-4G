function validate() {
	let error = false; //this is the error variable
	const subject = document.getElementById("subject-error");
	if (!document.getElementById("subject").value.trim()) {
		subject.textContent = "Fach eingeben!"; //set error message
		error = true;
	} else
            subject.textContent = "";

	const teacher = document.getElementById("teacher-error");
	if (!document.getElementById("teacher").value.trim()) {
		teacher.textContent = "Lehrer eingeben!"; //set error message
		error = true;
	} else
            teacher.textContent = "";

	return !error; //return true if no error, false if error
}