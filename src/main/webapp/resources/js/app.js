"use strict"
var app = app || {}
app = (()=>{
	let _,js
	let init=()=>{
		_ = $.ctx()
		js = $.js()
	}
	let onCreate =x=>$.getScript(x+'/resources/js/router.js', ()=>{
		$.extend(new Session(x)),
		setContentView()
	})
	let setContentView=()=>{
		init()
		$('<div/>')
		.css({height:'150px'})
		.appendTo('#wrapper')
		$('<table id = "tab"><tr></tr></table>')
		.css({width:'90%',
			height: '700px',
			margin: '0 auto'})
		.appendTo('#wrapper')
		$('<td/>',{id : 'left'})
		.css({width:'10%',
			height: '100%',
			margin: '0 auto',
			'vertical-align':'top',
		    'text-align': 'center'
		})
		.appendTo('tr')
		$('<td/>',{id : 'right'})
		.css({width:'80%',
			height: '100%',
			border: '1px solid #dddddd',
			margin: '0 auto'
		})
		.appendTo('tr')
		
		$.each([{name:'HOME',color:'rgba(64, 177, 117, 0.27)'},
			{name:'NAVER',color:'rgba(255, 235, 59, 0.33)'},
			{name:'CGV',color:'rgba(0, 188, 212, 0.21)'},
			{name:'BUGS',color:'rgba(156, 39, 176, 0.11)'}],(i,j)=>{
			$('<div/>')
			.html('<h3>'+j.name+'</h3>')
			.css({
				width:'100%',
				height: '60px',
				border: '1px solid #dddddd'
				
			})
			.appendTo('#left')
			.click(function() {
				$(this).css({'background-color':j.color})
				$(this).siblings().css({'background-color':'white'})
				switch($(this).text()){
				case'HOME':
					$('#right').empty()
					break;
				case'NAVER':
					$.getJSON(_+'/test/naver', d=>{
						$('#right').empty()
						$.each(d,(i,j)=>{
							$('<div><h2>'+j.word+'</h2><br/><h4>'+j.text+'</h4></div>')
							.appendTo('#right')
							.css({width:'49.5%',
								height: '50%',
								border: '2px solid #dddddd',
								margin: '0 auto',
								'text-align':'center',
								float : 'left',
								'background-color':'seashell'})
						})
						
					})
					break;
				case'CGV':
					$.getJSON(_+'/test/cgv', d=>{
						$('#right').empty()
						$.each(d,(i,j)=>{
							$('<div><h2>'+j.seq+'위</h2></br>'+
							'<img src='+j.img+'></br><h5>'+
							j.title+'</br>'+
							j.percent+'</br>'+
							j.txtinfo+'</h5></br>'+
							'</div>')
							.css({width:'33%',
								height: '33%',
								border: '2px solid #dddddd',
								margin: '0 auto',
								'text-align':'center',
								float : 'left',
								'background-color':'ghostwhite'})
							.appendTo('#right')
						})
					})
					break;
				case'BUGS':
					
					$.getJSON(_+'/test/bugs', d=>{
						$('#right').empty()
						$('<table id="bugs"></table>').appendTo('#right')
						$.each(d,(i,j)=>{
							$('<tr><td><img src='+j.img+'/></td><td>'+j.ranking+'위 </td><td>'+j.title+'</td><td>'+j.artist+'</td><td>'+j.album+'</td></tr>')
							.appendTo('#bugs')
							.css({width:'100%',
								height: '50px',
								border: '1px solid #dddddd',
								margin: '0 auto',
								'text-align':'center',
								'background-color':'ghostwhite'})
						})
						
					})
					break;
				}
			})
		})
		
	}
	return {onCreate}
})();