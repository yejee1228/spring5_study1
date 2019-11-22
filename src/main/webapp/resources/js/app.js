"use strict"
var app = app || {}
app = (()=>{
	let _,js
	let init=()=>{
		_ = $.ctx()
		js = $.js()
	}
	let run =x=>{
		$.when(
			$.getScript(x+'/resources/js/router.js',()=>{
				$.extend(new Session(x))
			}),
			$.getScript(x+'/resources/js/pop.js')
		).done(()=>{
			onCreate()
		}).fail(()=>{})
	}
	let onCreate=()=>{
		init()
		$(pop.view()).appendTo('#wrapper')
		pop.open()
		setContentView()
	}
	let setContentView=()=>{
		$('#wrapper').css({"background-image" : "url(https://g-grafolio.pstatic.net/20191104_97/1572834605348Gz451_JPEG/13%28pc%29.jpg)"})
		$('<div/>')
		.css({height:'100px'})
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
			margin: '0 auto',
			'vertical-align': 'top'
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
						$('<div/>',{id:'naverWord'}).appendTo('#right').css({
							width: '800px',
							height: '500px',
							'margin': '100px auto',
							'text-align':'center'
						})
						$.each(d,(i,j)=>{
							$('<div><h2>'+j.word+'</h2><br/><h4>'+j.text+'</h4></div>')
							.appendTo('#naverWord')
							.css({width:'49%',
								height: '50%',
								border: '3px solid white',
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
							$('<span><h2>'+j.seq+'위</h2></br>'+
							'<img src='+j.img+'></br><h5>'+
							j.title+'</br>'+
							j.percent+'</br>'+
							j.txtinfo+'</h5></br>'+
							'</span>')
							.css({width:'19%',
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
					bugs(0)
					break
				}
			})
		})
		
	}
	let bugs=x=>{
		$('#right').empty()
		$.getJSON(_+'/test/bugs/page/'+ x, d=>{
			let pager = d.pager
			let list = d.list
			$('<table id="bugs"><tr></tr></table>').appendTo('#right')
			.css({width:'90%', height:'60%', margin:'0 auto'})
			$.each([
				'','순위','타이틀','아티스트','앨범명'
			],(i,j)=>{
				$('<th/>')
				.html('<b>'+j+'</b>')
				.appendTo('#bugs tr')
				.css({height:'50px'})
			})
			$.each(list,(i,j)=>{
				$('<tr><td style = "width: 50px"><img src='+j.img+'/></td><td style="width: 70px">'+j.ranking+'위 </td><td style="width: 45%">'+j.title+'</td><td>'+j.artist+'</td><td>'+j.album+'</td></tr>')
				.appendTo('#bugs')
				.css({width:'100%',
					height: '50px',
					border: '1px solid #dddddd',
					margin: '0 auto',
					'text-align':'center',
					'background-color':'linen'})
			})
			$('<div/>')
			.css({height:'50px'})
			.appendTo('#right')
			$('<div/>',{id:'page'}).appendTo('#right')
			.css({margin:'0 auto', 'display': 'table'})
			
			if(pager.existPrev){
				$('<div>◀</div>')
				.css({
				    width: '30px',
				    height: '30px',
				    margin: '0 auto',
				    float: 'left',
				    color : '#dddddd',
				    'text-align': 'center',
				    'font-size': 'larger'
				})
				.appendTo('#page')
				.click(function(){
					app.bugs(pager.prevBlock)
				})
				
			}
			let i = pager.startPage
			for( ; i<=pager.endPage; i++){
				$('<div></div>')
				.text(i+1)
				.css({
				    width: '30px',
				    height: '30px',
				    'background-color': 'blanchedalmond',
				    margin: '0px auto',
				    'border-radius': '100%',
				    float: 'left',
				    'text-align': 'center',
				    'font-size': 'larger',
				    color : '#9e9e9ed9'
				})
				.appendTo("#page")
				.click(function(){
					
					app.bugs($(this).text()-1)
				})
			}
			if(pager.existNext){
				$('<div>▶</div>')
				.css({
				    width: '30px',
				    height: '30px',
				    margin: '0 auto',
				    float: 'left',
				    color : '#dddddd',
				    'text-align': 'center',
				    'font-size': 'larger'
				})
				.appendTo('#page')
				.click(function(){
					app.bugs(pager.nextBlock)
				})
				
			}
			
		})
	}
	return {run, bugs}
})();