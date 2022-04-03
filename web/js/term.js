/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function pagger(id, pageindex, totalpage, gap, cache, searh,sp)
{
    var container = document.getElementById(id);
    var result = '';
    if (pageindex - gap > 1) {
        if (searh == 1) {
            result += '<a href="ControllerProduct?submit=Submit&searchProName=' + sp + '">' + 'First' + '</a>';
        } else {
            if (cache == 0) {
                result += '<a href="ControllerProduct?page=1">' + 'First' + '</a>';
            } else
                result += '<a href="ControllerProduct?page=1&cateid=' + cache + '">' + 'First' + '</a>';
        }
    }

    for (var i = pageindex - gap; i < pageindex; i++)
        if (i > 0) {
            if (searh == 1) {
                result += '<a href="ControllerProduct?page=' + i + '&submit=Submit&searchProName=' + sp + '">' + i + '</a>';
            } else {
                if (cache == 0) {
                    result += '<a href="ControllerProduct?page=' + i + '">' + i + '</a>';
                } else
                    result += '<a href="ControllerProduct?page=' + i + '&cateid=' + cache + '">' + i + '</a>';
            }
        }
    result += '<span>' + pageindex + '</span>';
    for (var i = pageindex + 1; i <= pageindex + gap; i++)
        if (i <= totalpage) {
            if (searh == 1) {
                result += '<a href="ControllerProduct?page=' + i + '&submit=Submit&searchProName=' + sp + '">' + i + '</a>';
            } else {
                if (cache == 0) {
                    result += '<a href="ControllerProduct?page=' + i + '">' + i + '</a>';
                } else
                    result += '<a href="ControllerProduct?page=' + i + '&cateid=' + cache + '">' + i + '</a>';
            }
        }
    if (pageindex + gap < totalpage)
    {
        if (searh == 1) {
            result += '<a href="ControllerProduct?page=' + totalpage + '&submit=Submit&searchProName=' + sp + '">' + 'Last' + '</a>';

        } else {
            if (cache == 0) {
                result += '<a href="ControllerProduct?page=' + totalpage + '">' + 'Last' + '</a>';
            } else
                result += '<a href="ControllerProduct?page=' + totalpage + '&cateid=' + cache + '">' + 'Last' + '</a>';

        }

    }
    container.innerHTML = result;
}
