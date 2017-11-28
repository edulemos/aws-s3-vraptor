</div>
</div>
</div>
</div>


<c:if test="${!empty msg}">
	<script>
		addMsgSucesso("${msg}");
	</script>
</c:if>

<c:if test="${!empty msgErro}">
	<script>
		addMsgErro("${msgErro}");
	</script>
</c:if>


</body>


</html>
