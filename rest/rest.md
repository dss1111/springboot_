# rest
학교 센서데이터 다룰때 Restful api를 썼었던 기억이..

GET - 조회  
(특정조건으로 검색하는 방법은 방법이 안정해져 있어서 설계하기 나름이라고 한다. 그래서 작년에 검색해도 안나오고 고생했구나..)  
POST - 생성  
DELETE - 삭제  
PUT - 수정   

```java
@RestController //Rest컨트롤러
@CrossOrigin("*") //자원 외부 도메인에서 접근 허용
@RequestMapping("/")
public class TodoRestController {

	private TodoService todoService;
	@Autowired
	public void setTodoService(TodoService todoService) {
		this.todoService = todoService;
	}
```
RestController 어노테이션을 걸어주면 @ResponseBody가 자동으로 붙는다 따라서 메소드마다 @ResponseBody 어노테이션을 달아주지 않아도 된다.

CrossOrigin 어노테이션은 외부에서 자원을 사용할 수 있게 해준다. Rest를 8080에서 켜놓고 Vue를 다른포트에서 켜놓은 다음 Rest의 자원을 요청할 수 있는 식.

```java
	@DeleteMapping("/user/{no}")
	public ResponseEntity<byte[]> removeTodo(@PathVariable int no) { //no를 int로 받는다.
		if(todoService.findTodo(no)==null) {
			return new ResponseEntity<byte[]>("해당 번호의 할일이 없습니다".getBytes(), HttpStatus.NOT_FOUND);
		}
		todoService.removeTodo(no);
		return new ResponseEntity<byte[]>("할일 삭제에 성공하였습니다".getBytes(), HttpStatus.OK);
	}
```
@PathVariable를 통해 url값을 받는다. 참고로 "/user/{no}" @PathVariable int no 둘다 이름이 no로 같은데 이름 다르면 안된다!

```java
	@DeleteMapping("/{user}/{no}")
	public ResponseEntity<byte[]> removeTodo(@PathVariable String user,@PathVariable int no) { //PathVariable 여러개도 사용할수 있음
		//..............생략................
	}
```


## RequestParam vs PathVariable
``Type 1 => http://127.0.0.1?user=1&no=2``RequestParam으로 user랑 no를 받는다  
``Type 2 => http://127.0.0.1/user/1 ``PathVariable로 받는다     
둘다 혼용해서 사용할 수도 있음..


