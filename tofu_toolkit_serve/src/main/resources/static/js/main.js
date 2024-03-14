let uuid = '2193BF4F-D90D-4B2F-A4F1-6408184BF479';

function startup() {
    axios.post('/rocketmq/start', {
        'addr': $("#addr").val(),
        'group': $("#group").val(),
        'uuid': uuid
    }).then(function (response) {
        let data = response.data;
        console.log()
        appendConsole(data.msg, data.code === '200' ? 'green' : 'red');
        mq_box_display(data.code === '200')
    }).catch(function (error) {
        appendConsole(error, 'red')
    });
}

function shutdown() {
    axios.post('/rocketmq/shutdown', {
        'uuid': uuid
    }).then(function (response) {
        let data = response.data;
        appendConsole(data.msg, data.code === '200' ? 'green' : 'red');
        mq_box_display(!(data.code === '200'))
    }).catch(function (error) {
        appendConsole(error, 'red')
    });
}

function send() {
    axios.post('/rocketmq/send', {
        'topic': $("#topic").val(),
        'tags': $("#tags").val(),
        'message': $("#message").val(),
        'uuid': uuid
    }).then(function (response) {
        let data = response.data;
        let state = data.code === '200';
        appendConsole(state ? JSON.stringify(data.data, null, '\t') : data.msg, state ? 'green' : 'red');
    }).catch(function (error) {
        appendConsole(error, 'red')
    });
}

function mq_box_display(bool) {
    if (bool) {
        $(".mq_send_box").show()
        $(".mq_start_box").hide();
    } else {
        $(".mq_send_box").hide();
        $(".mq_start_box").show();
    }
}

function appendConsole(msg, color) {
    let tag = '<p style="border: 1px dashed ' + color
        + '; margin: 10px; font-size: small; padding: 2px 5px;'
        + 'color:' + color + '">'
        + nowDate()
        + ' - '
        + msg + '</p>'
    $('.console').append(tag)
}

function nowDate(){
     let now = new Date();
     let year = now.getFullYear();
     let month = now.getMonth() + 1;
     let day = now.getDate();
     let hour = now.getHours();
     let minute = now.getMinutes();
     let second = now.getSeconds();
     return year + '-' + month + '-' + day + ' ' + hour + ':' + minute + ':' + second;
}