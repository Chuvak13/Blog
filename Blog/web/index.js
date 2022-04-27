function getAllCommentsByNewsId(newsId) {
    fetch('/CommentsServlet?newsId=' + newsId)
        .then((response) => {
            return response.json();
        })
        .then((data) => {
            console.log(data);

            let getCommentsButton = document.getElementById('getCommentsButton');

            for (let i = 0; i < data.length; i++) {
                getCommentsButton.insertAdjacentHTML('afterend',
                    '<p>'+ data[i].comment +'</p>')
            }

        });
}