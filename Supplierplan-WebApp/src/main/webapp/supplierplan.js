function validate() {
	let error = false;
	const subject = document.getElementById("subject-error");
	if (!document.getElementById("subject").value.trim()) {
		subject.textContent = "Fach eingeben!"
		error = true;
	} else
            subject.textContent = "";

	const teacher = document.getElementById("teacher-error");
	if (!document.getElementById("teacher").value.trim()) {
		teacher.textContent = "Lehrer eingeben!";
		error = true;
	} else
            teacher.textContent = "";

	return !error;
}