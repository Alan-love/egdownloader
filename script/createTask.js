var mark = {//��־��
	name : ['<h1 id="gn">', '</h1><h1'],//����
	subname : ['</h1><h1 id="gj">', '</h1></div>'],//С����
	type : ['png" alt="', '" class="ic'],//���
	coverUrl : ['<div id="gd1"><img src="', '" alt="" /></div></div>'],//����url
	total_size : ['Images:</td><td class="gdt2">', '</td></tr><tr><td class="gdt1">Resized:'],//��Ŀ@��С
	language : ['Language:</td><td class="gdt2">', '</td></tr></table></div><div id="gdr"']//��������
};

function interceptFromSource(source, prefix, suffix){
	var s = source;
	s = s.substr(s.indexOf(prefix) + prefix.length, s.length);
    return s.substring(0, s.indexOf(suffix));
}

function trim(s){
	if(s != null && typeof s == 'string'){
		return s.replace(/(^\s*)|(\s*$)/g, "");
	}
	return s;
};

function parseJson(json){
	if(json == null)
		return "";
	var s = "{";
	for(var k in json){
		s += '"' + k + '":';
		if(typeof json[k] == 'number'){
			s += json[k] + ',';
		}else{
			s += '"' + json[k] + '",';
		}
	}
	s = s.substr(0, s.length - 1);
	return s + "}";
}

function parse(source){
	var task = {};
	//��ȡ����
	task.name = interceptFromSource(source, mark.name[0], mark.name[1]);
	//��ȡС����
	task.subname = interceptFromSource(source, mark.subname[0], mark.subname[1]);
	//��ȡ����·��
	task.coverUrl = interceptFromSource(source, mark.coverUrl[0], mark.coverUrl[1]);
	//��ȡ���
	task.type = interceptFromSource(source, mark.type[0], mark.type[1]);
	//��ȡ��Ŀ����С
	var temp = interceptFromSource(source, mark.total_size[0], mark.total_size[1]);
	task.total = parseInt(trim(temp.split("@")[0]));
	task.size = trim(temp.split("@")[1]);
	//��ȡ��������
	task.language = interceptFromSource(source, mark.language[0], mark.language[1]);
	return parseJson(task);
}
parse(htmlSource);