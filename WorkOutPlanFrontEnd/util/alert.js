export let setAlert = (type,content,row) => {
    switch (type) {
        case 'success':
            Swal.fire({
                position: "top-end",
                icon: "success",
                title: content,
                showConfirmButton: false,
                timer: 1500
            });
            break;
        case 'error':
            Swal.fire({
                position: "top-end",
                icon: "error",
                title: content,
                showConfirmButton: false,
                timer: 1500
            });
            break;
        case "tryAgain" :
            Swal.fire({
                position: "top-end",
                title: content,
                icon: "question"
            });
    }
}