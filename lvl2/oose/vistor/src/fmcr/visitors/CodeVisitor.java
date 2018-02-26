package fmcr.visitors;

import com.github.javaparser.Position;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import fmcr.factory.CodeAnalysisFactory;
import fmcr.main.Client;
import fmcr.util.MParameter;
import fmcr.util.Report;
import fmcr.util.ReportTag;
import fmcr.util.Variable;

import java.util.ArrayList;
import java.util.List;

/**
 * A visitor that traverse an AST to extract its program properties
 *
 * @author Stuart Reilly, 2258082
 * @version 1.0
 * 
 * @see     fmcr.visitors.InitVisitor
 */
public class CodeVisitor extends VoidVisitorAdapter<Void>{
	CodeAnalysisFactory caf;

	public CodeVisitor(CodeAnalysisFactory caf) {
		this.caf = caf;
	}
	
	/**
	 * Workshop 3: Visitors Design Pattern with JavaParser (Q1)
	 */
	@Override
	public void visit(FieldDeclaration fd, Void arg) {
		super.visit(fd, arg); //Do not delete
		Report fr = new Report(ReportTag.FIELD);//Do not delete


		/*
		 * Q1a: Populate fr with all the field variables (0.5 mark).
		 */
		fd.getVariables().forEach(var -> fr.addVariable(new Variable(var.getName().asString(), var.getType().asString())));

		
		/*
		 * Q1b: Populate fr with all the field modifiers (0.5 mark).
		 */
		fd.getModifiers().forEach(mod -> fr.addModifier(mod.asString()));

		
		/*
		 * Q1c: Retrieve the line position of the field in the source code, 
		 * then use the result to call setLine() method on fr (1 mark).
		 */
        fr.setLine(fd.getBegin().orElse(Position.pos(-1, -1)).line);
		
		
		/*
		 * Q1d: Call setInappropriateAccessLevel() method in fr. The method 
		 * should take boolean value true only when the field is non-private
		 * and non-final, otherwise a parameter value should be false (1 mark).
		 */
        fr.setInappropriateAccessLevel(!(fd.isPrivate() || fd.isFinal()));
		
		
		/*
		 * Q1e: Call setDocumented() method in fr. The method should take boolean 
		 * value  true only when the field has a Javadoc comment, otherwise a
		 * parameter value should be false (1 mark).
		 */
		fr.setDocumented(fd.hasJavaDocComment());
		
		
		
		
		caf.updateFieldsTable(fr);//Do not delete
	}
	
	/**
	 * Workshop 3: Visitors Design Pattern with JavaParser (Q2)
	 */
	@Override		
	public void visit(MethodDeclaration md, Void arg) {
		super.visit(md, arg);//Do not delete
		Report mr = new Report(ReportTag.METHOD);//Do not delete
		

		/*
		 * Q2a: Call setMethodName() method in mr. The method should
		 * take as parameter the name of the method declaration (0.5 mark).
		 */
		mr.setMethodName(md.getNameAsString());
		
		/*
		 * Q2b: Call setDocumented() method in mr. The method should 
		 * take boolean value  true only when the method declaration has
		 * a Javadoc comment, otherwise a parameter value should be false (1 mark).
		 */
        mr.setDocumented(md.hasJavaDocComment());
		
		/*
		 * Q2c: Populate mr with all the method's modifiers (0.5 mark).
		 */
        md.getModifiers().forEach(mod -> mr.addModifier(mod.asString()));
		

		/*
		 * Q2d:Populate mr with all the method's parameters (0.5 mark)
		 */
	    md.getParameters().forEach(param -> mr.addParameter(new MParameter(param.getNameAsString(), param.getType().asString())));
		
		
		/*
		 * Q2e: Whenever a method declaration has a body, call the setUnusedParameter()
		 * method in mr. It should take boolean value true only when the method declaration
		 * has a parameter that is unused in its body. Otherwise the value should be false (2 mark).
		 */
        mr.setUnusedParameter(md.getBody()
                .map(body -> md.getParameters()
                        .stream()
                        .allMatch(body::containsWithin))
                .orElse(Boolean.FALSE));
		
		/*
		 * Q2f: Call setReturnType() method in mr. The method should take as parameter the
		 * return type of the method declaration (0.5 mark).
		 */
        mr.setReturnType(md.getType().asString());

		
		
		/*
		 * Q2g: Retrieve the start and end position of the declared method. Use the results 
		 * to call setStartLine() and setSize() on mr respectively. The size of a method is
		 * the difference between the start and end position) (1 mark).
		 */
        mr.setStartLine(md.getRange().map(range -> range.begin.line).orElse(-1));
	    mr.setSize(md.getRange().map(range -> range.end.line - range.begin.line).orElse(-1));
		
		
		
		caf.updateMethodsTable(mr);//Do not Delete
	}
	
	
	@Override
	public void visit(ClassOrInterfaceDeclaration cid, Void arg) {
		super.visit(cid, null);
		Client.isinner = cid.isInnerClass();
		if(Client.isinner) {
			Client.innerClassName = cid.getNameAsString();//+"[INNER]";
		}			
	}
	
	/**
	 * 
	 * @param node - parent 
	 * @param leaves - children
	 * This function uses recursion to retrieve the leaves from a parent node
	 */
	public void retrieveleaveNodes(Node node, ArrayList<Node> leaves) {
		List<Node> children = node.getChildNodes();		
		if(children.isEmpty()) {
			leaves.add(node);
		}
		else {
			children.forEach(c1 ->{
				retrieveleaveNodes(c1,leaves);
			});
		}
	}	
}
