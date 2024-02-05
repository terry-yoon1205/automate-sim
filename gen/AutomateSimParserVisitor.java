// Generated from /Users/Admin/Desktop/courses/2023w2/cpsc410/project1/src/parser/AutomateSimParser.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link AutomateSimParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface AutomateSimParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link AutomateSimParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(AutomateSimParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link AutomateSimParser#decl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecl(AutomateSimParser.DeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link AutomateSimParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(AutomateSimParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link AutomateSimParser#property}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProperty(AutomateSimParser.PropertyContext ctx);
	/**
	 * Visit a parse tree produced by {@link AutomateSimParser#enum}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEnum(AutomateSimParser.EnumContext ctx);
	/**
	 * Visit a parse tree produced by {@link AutomateSimParser#string}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitString(AutomateSimParser.StringContext ctx);
	/**
	 * Visit a parse tree produced by {@link AutomateSimParser#number}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumber(AutomateSimParser.NumberContext ctx);
	/**
	 * Visit a parse tree produced by {@link AutomateSimParser#arg}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArg(AutomateSimParser.ArgContext ctx);
	/**
	 * Visit a parse tree produced by {@link AutomateSimParser#room}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRoom(AutomateSimParser.RoomContext ctx);
	/**
	 * Visit a parse tree produced by {@link AutomateSimParser#device}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDevice(AutomateSimParser.DeviceContext ctx);
	/**
	 * Visit a parse tree produced by {@link AutomateSimParser#device_prop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDevice_prop(AutomateSimParser.Device_propContext ctx);
	/**
	 * Visit a parse tree produced by {@link AutomateSimParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(AutomateSimParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link AutomateSimParser#action}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAction(AutomateSimParser.ActionContext ctx);
	/**
	 * Visit a parse tree produced by {@link AutomateSimParser#if}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIf(AutomateSimParser.IfContext ctx);
	/**
	 * Visit a parse tree produced by {@link AutomateSimParser#for}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFor(AutomateSimParser.ForContext ctx);
	/**
	 * Visit a parse tree produced by {@link AutomateSimParser#set}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSet(AutomateSimParser.SetContext ctx);
}